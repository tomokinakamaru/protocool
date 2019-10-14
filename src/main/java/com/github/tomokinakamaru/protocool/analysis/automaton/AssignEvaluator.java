package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.automaton.Automaton;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import java.util.Collection;

public class AssignEvaluator extends Analysis {

  @Override
  public void enterChain(ChainContext ctx) {
    if (ctx.qualifiedName() != null) {
      Automaton a = ctx.expression().automaton;
      a.getEpsilonClosureTo(a.finals)
          .stream()
          .map(a::getTransitionsTo)
          .flatMap(Collection::stream)
          .filter(t -> t.symbol != null)
          .filter(t -> t.symbol.hasMethodContext())
          .map(t -> t.symbol.getMethodContext())
          .forEach(m -> m.evaluator = ctx.qualifiedName().getText());
    }
  }
}
