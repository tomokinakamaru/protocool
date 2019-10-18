package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.data.ReturnExpressions;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import java.util.Collection;

public class StoreReturnExpressions extends AutomatonAnalyzer {

  @Override
  public void init() {
    set(new ReturnExpressions());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton a) {
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
