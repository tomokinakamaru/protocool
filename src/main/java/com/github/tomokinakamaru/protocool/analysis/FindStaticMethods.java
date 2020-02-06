package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.context.StaticMethods;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.utility.TransitionAnalysis;

public class FindStaticMethods extends TransitionAnalysis {

  @Override
  public void initialize() {
    set(new StaticMethods());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton automaton, Transition transition) {
    if (ctx.STATIC() != null) {
      if (automaton.initials.contains(transition.source)) {
        get(StaticMethods.class).add(transition.symbol.asMethodContext());
      }
    }
  }
}
