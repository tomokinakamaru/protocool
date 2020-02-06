package com.github.tomokinakamaru.protocool.utility;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.context.Automatons;
import com.github.tomokinakamaru.protocool.data.Automaton;

public abstract class AutomatonAnalysis extends FileContextAnalysis {

  protected void analyze(ClassContext ctx, Automaton automaton) {}

  protected void analyze(ChainContext ctx, Automaton automaton) {}

  @Override
  public void enterClass(ClassContext ctx) {
    Automaton a = get(Automatons.class).get(ctx);
    if (a != null) {
      analyze(ctx, a);
    }
  }

  @Override
  public void enterChain(ChainContext ctx) {
    Automaton a = get(Automatons.class).get(ctx);
    if (a != null) {
      analyze(ctx, a);
    }
  }
}
