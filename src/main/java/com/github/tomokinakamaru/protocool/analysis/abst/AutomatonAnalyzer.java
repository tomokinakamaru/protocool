package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automata;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;

public abstract class AutomatonAnalyzer extends Listener {

  protected void analyze(ClassContext ctx, Automaton a) {}

  protected void analyze(ChainContext ctx, Automaton a) {}

  @Override
  public final void enterClass(ClassContext ctx) {
    analyze(ctx, get(Automata.class).get(ctx));
  }

  @Override
  public final void enterChain(ChainContext ctx) {
    analyze(ctx, get(Automata.class).get(ctx));
  }
}
