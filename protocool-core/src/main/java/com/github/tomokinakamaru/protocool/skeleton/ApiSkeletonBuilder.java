package com.github.tomokinakamaru.protocool.skeleton;

import static com.github.tomokinakamaru.protocool.skeleton.Utility.buildParameters;
import static com.github.tomokinakamaru.protocool.skeleton.Utility.getText;
import static com.github.tomokinakamaru.protocool.skeleton.Utility.isNotLastItem;

import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Parameters;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ReferenceContext;
import java.util.List;

public class ApiSkeletonBuilder extends FileBuilder {

  private final ClazzContext context;

  private final Automaton automaton;

  private final State state;

  public ApiSkeletonBuilder(ClazzContext context, Automaton automaton, State state) {
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
    params.removeAll(transition.source.parameters);
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
        if (ref.qualifiedName().getText().equals("void")) {
          append(method.evaluator, "();");
        } else {
          append("return", method.evaluator, "();");
        }
      } else if (ref.clazzDestination != null) {
        append("return", "new");
        append(buildAPIRawName(transition.destination, context));
        append(buildParameters(dst.parameters, false));
        append("();");
      } else {
        append("return", method.evaluator, "();");
      }
    } else {
      String eval = method.evaluator;
      if (method.evaluator != null) {
        append(eval, "();");
      }
      append("return", "new");
      append(buildAPIRawName(transition.destination, context));
      append(buildParameters(dst.parameters, false));
      append("();");
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
