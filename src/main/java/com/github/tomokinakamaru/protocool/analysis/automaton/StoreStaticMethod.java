package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.data.StaticMethods;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;

public class StoreStaticMethod extends AutomatonAnalyzer {

  @Override
  public void init() {
    set(new StaticMethods());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton a) {
    if (ctx.STATIC() != null) {
      a.getTransitionsFrom(a.initials)
          .stream()
          .map(t -> t.symbol.asMethodContext())
          .forEach(m -> get(StaticMethods.class).add(m));
    }
  }
}
