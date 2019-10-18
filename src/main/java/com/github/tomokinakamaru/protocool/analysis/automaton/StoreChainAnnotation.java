package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.data.ReturnExpressions;
import com.github.tomokinakamaru.protocool.data.StaticMethods;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import java.util.Collection;

public class StoreChainAnnotation extends AutomatonAnalyzer {

  @Override
  public void init() {
    set(new StaticMethods());
    set(new ReturnExpressions());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton a) {
    if (ctx.STATIC() != null) {
      a.getTransitionsFrom(a.initials)
          .stream()
          .map(t -> t.symbol.asMethodContext())
          .forEach(m -> get(StaticMethods.class).add(m));
    }

    if (ctx.qualifiedName() != null) {
      a.getTransitionsTo(a.finals)
          .stream()
          .map(t -> t.source)
          .map(a::getTransitionsTo)
          .flatMap(Collection::stream)
          .filter(t -> t.symbol.isMethodContext())
          .map(t -> t.symbol.asMethodContext())
          .forEach(m -> get(ReturnExpressions.class).put(m, ctx.qualifiedName().getText()));
    }
  }
}
