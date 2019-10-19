package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.TransitionAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.data.StaticMethods;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;

public class StoreStaticMethods extends TransitionAnalyzer {

  @Override
  public void initialize() {
    set(new StaticMethods());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton a, Transition t) {
    if (ctx.STATIC() != null) {
      if (a.initials.contains(t.source)) {
        get(StaticMethods.class).add(t.symbol.asMethodContext());
      }
    }
  }
}
