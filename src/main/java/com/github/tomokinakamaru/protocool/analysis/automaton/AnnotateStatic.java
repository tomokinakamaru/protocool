package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import java.util.Collection;

public class AnnotateStatic extends Analysis {

  @Override
  public void enterChain(ChainContext ctx) {
    if (ctx.STATIC() != null) {
      Automaton a = ctx.expression().automaton;
      a.getEpsilonClosureFrom(a.initials)
          .stream()
          .map(a::getTransitionsFrom)
          .flatMap(Collection::stream)
          .filter(t -> t.symbol != null)
          .filter(t -> t.symbol.hasMethodContext())
          .map(t -> t.symbol.getMethodContext())
          .forEach(m -> m.isStatic = true);
    }
  }
}
