package com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder;

import static com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.Utility.buildParameters;
import static com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.Utility.getText;
import static com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.Utility.isNotLastItem;

import com.github.tomokinakamaru.protocool.analysis.data.Automaton;
import com.github.tomokinakamaru.protocool.analysis.data.Parameters;
import com.github.tomokinakamaru.protocool.analysis.data.State;
import com.github.tomokinakamaru.protocool.analysis.data.Transition;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;
import java.util.List;
import java.util.Set;

public class ApiBuilder extends FileBuilder {

  private final ClazzContext context;

  private final Automaton automaton;

  private final State state;

  public ApiBuilder(ClazzContext context, Automaton automaton, State state) {
    this.context = context;
    this.automaton = automaton;
    this.state = state;
  }

  @Override
  public void build() {
    buildPath();
    buildHeader();
    buildClassHead();
    append("{");
    buildClassBody();
    append("}");
  }

  private void buildPath() {
    String fileName = buildAPIRawName(state, context) + ".java";
    setPath(context.ownerSpecification.packagePath.resolve(fileName));
  }

  private void buildHeader() {
    String pkg = context.ownerSpecification.packageName;
    if (!pkg.isEmpty()) {
      append("package", pkg, ";");
    }
  }

  private void buildClassHead() {
    append("public");
    if (!state.isInitial()) {
      append("final");
    }
    append("class");

    append(buildAPIRawName(state, context));

    append(buildParameters(state.parameters, true));

    if (state.isInitial()) {
      if (context.head().superClazz() != null) {
        append("extends");
        append(getText(context.head().superClazz().reference()));
      }
      if (context.head().interfaces() != null) {
        append("implements");
        List<ReferenceContext> names = context.head().interfaces().reference();
        for (ReferenceContext c : names) {
          append(getText(c));
          if (isNotLastItem(names, c)) {
            append(",");
          }
        }
      }
    } else {
      if (state.context != null) {
        append("extends", getText(state.context));
      }
    }
  }

  private void buildClassBody() {
    if (state.context == null) {
      append("java.util.List<Method$>", "methods", ";");
    }
    if (state.context != null) {
      String nodeName = state.context.ownerChain.nodeName;
      append(nodeName);
      Parameters parameters = new Parameters();
      Set<ChainContext> chains = context.ownerSpecification.classNodeTable.get(nodeName);
      for (ChainContext c : chains) {
        parameters.addAll(c.parameters);
      }
      append(buildParameters(parameters, false));
      append("clazz", ";");
    }
    for (Transition t : automaton.getTransitionsFrom(state)) {
      buildMethod(t);
    }
  }

  private void buildMethod(Transition transition) {
    buildMethodHead(transition);
    append("{");
    buildMethodBody(transition);
    append("}");
  }

  private void buildMethodHead(Transition transition) {
    append("public");
    if (transition.source.isInitial()) {
      if (transition.symbol.getMethodContext().isStatic) {
        append("static");
      }
    }

    Parameters params = transition.destination.parameters.copy();
    if (!transition.symbol.getMethodContext().isStatic) {
      params.removeAll(transition.source.parameters);
    }
    append(buildParameters(params, true));

    ReferenceContext c = transition.destination.context;
    if (c != null) {
      append(getText(c));
    } else {
      append(buildAPIRawName(transition.destination, context));
      append(buildParameters(transition.destination.parameters, false));
    }

    append(transition.symbol.getMethodContext().name().getText());

    append("(");
    for (ArgumentContext a : transition.symbol.getMethodContext().argument()) {
      append(getText(a.reference()));
      if (a.ELLIPSIS() != null) {
        append("...");
      }
      append(a.name().getText());
      if (isNotLastItem(transition.symbol.getMethodContext().argument(), a)) {
        append(",");
      }
    }
    append(")");
  }

  private void buildMethodBody(Transition transition) {
    State dst = transition.destination;
    MethodContext method = transition.symbol.getMethodContext();

    ReferenceContext ref = dst.context;

    String nodeType = method.nodeName + buildParameters(method.parameters, false);
    append(nodeType, "$ = new", nodeType, "();");
    for (ArgumentContext c : method.argument()) {
      append("$.", c.name().getText(), "=", c.name().getText(), ";");
    }

    if (ref != null) {
      if (ref.foreignTypeDestination != null) {
        append("this.methods.add($);");
        String nodeName = "Class$" + ref.qualifiedName().getText().replace(".", "_");
        String type = nodeName + buildParameters(ref.parameters, false);
        append(type, "$$", "=", "new", type, "();");
        if (!ref.qualifiedName().getText().equals("void")) {
          append("return");
        }
        append(method.evaluator, "($$);");
      } else if (ref.clazzDestination != null) {
        String type = buildAPIRawName(transition.destination, context);
        type += buildParameters(dst.parameters, false);
        append(type, "$$", "=", "new", type, "();");

        if (method.isStatic && state.isInitial()) {
          append("$$.methods = new java.util.ArrayList<Method$>();");
        } else {
          append("$$.methods = this.methods;");
        }
        append("$$.methods.add($);");
        append("return $$;");
      } else {
        append("this.methods.add($);");
        append("return", method.evaluator, "();");
      }
    } else {
      String eval = method.evaluator;
      if (method.evaluator != null) {
        append(eval, "();");
      }

      String type = buildAPIRawName(transition.destination, context);
      type += buildParameters(dst.parameters, false);
      append(type, "$$", "=", "new", type, "();");

      if (method.isStatic && state.isInitial()) {
        append("$$.methods = new java.util.ArrayList<Method$>();");
      } else {
        append("$$.methods = this.methods;");
      }
      append("$$.methods.add($);");
      append("return $$;");
    }
  }

  private static String buildAPIRawName(State state, ClazzContext context) {
    String name = context.head().name().getText();
    if (!state.isInitial()) {
      name += "$" + state.number;
    }
    return name;
  }
}