package com.github.tomokinakamaru.protocool.analysis.abst.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Automatons;

public abstract class AutomatonAnalyzer extends Listener {

  protected void analyze(ClassContext ctx, Automaton a) {}

  protected void analyze(ChainContext ctx, Automaton a) {}

  @Override
  public final void enterClass(ClassContext ctx) {
    Automaton a = get(Automatons.class).get(ctx);
    if (a != null) {
      analyze(ctx, a);
    }
  }

  @Override
  public final void enterChain(ChainContext ctx) {
    Automaton a = get(Automatons.class).get(ctx);
    if (a != null) {
      analyze(ctx, a);
    }
  }
}
