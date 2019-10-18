package com.github.tomokinakamaru.protocool.analysis.automaton;

import static com.github.tomokinakamaru.antlr4.utility.NodeFinder.findParent;

import com.github.tomokinakamaru.protocool.analysis.abst.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;
import com.github.tomokinakamaru.protocool.error.BadSpecification;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;

public class ValidateSignature extends StateAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a, State s) {
    Set<Transition> transitions = a.getTransitionsFrom(s);
    for (Transition t1 : transitions) {
      String s1 = buildSignature(t1.symbol.asMethodContext());
      for (Transition t2 : transitions) {
        String s2 = buildSignature(t2.symbol.asMethodContext());
        if (t1 != t2 && s1.equals(s2)) {
          throw new BadSpecification();
        }
      }
    }
  }

  private String buildSignature(MethodContext ctx) {
    TypeTable table = get(TypeTables.class).get(findParent(ClassContext.class, ctx));
    return ctx.name().getText() + "(" + buildSignature(ctx.argument(), table) + ")";
  }

  private String buildSignature(List<ArgumentContext> lst, TypeTable table) {
    return lst.stream()
        .map(a -> table.get(a.name().getText()))
        .map(this::buildSignature)
        .collect(Collectors.joining(","));
  }

  private String buildSignature(ParserRuleContext ctx) {
    if (ctx instanceof ParameterContext) {
      return ((ParameterContext) ctx).name().getText();
    } else if (ctx instanceof ClassContext) {
      return ((ClassContext) ctx).head().name().getText();
    } else if (ctx instanceof ImportContext) {
      return ((ImportContext) ctx).qualifiedName().getText();
    } else {
      throw new RuntimeException();
    }
  }
}
