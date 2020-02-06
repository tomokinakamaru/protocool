package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.context.ReturnExpressions;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.utility.TransitionAnalysis;

public class FindReturnExpressions extends TransitionAnalysis {

  @Override
  public void initialize() {
    set(new ReturnExpressions());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton automaton, Transition transition) {
    if (ctx.qualifiedName() != null) {
      if (automaton.finals.contains(transition.destination)) {
        for (Transition t : automaton.getTransitionsTo(transition.source)) {
          if (t.symbol.isMethodContext()) {
            String expr = ctx.qualifiedName().getText();
            get(ReturnExpressions.class).put(t.symbol.asMethodContext(), expr);
          }
        }
      }
    }
  }
}
