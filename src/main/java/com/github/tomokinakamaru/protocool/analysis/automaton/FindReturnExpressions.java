package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.TransitionAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.data.ReturnExpressions;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;

public class FindReturnExpressions extends TransitionAnalyzer {

  @Override
  public void initialize() {
    set(new ReturnExpressions());
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton a, Transition tr) {
    if (ctx.qualifiedName() != null) {
      if (a.finals.contains(tr.destination)) {
        for (Transition t : a.getTransitionsTo(tr.source)) {
          if (t.symbol.isMethodContext()) {
            MethodContext c = t.symbol.asMethodContext();
            String ret = ctx.qualifiedName().getText();
            get(ReturnExpressions.class).put(c, ret);
          }
        }
      }
    }
  }
}
