package com.github.tomokinakamaru.protocool.analysis.abst.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.tree.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automata;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;

public abstract class AutomatonAnalyzer extends Listener {

  protected void analyze(ClassContext ctx, Automaton a) {}

  protected void analyze(ChainContext ctx, Automaton a) {}

  @Override
  public final void enterClass(ClassContext ctx) {
    Automaton a = get(Automata.class).get(ctx);
    if (a != null) {
      analyze(ctx, a);
    }
  }

  @Override
  public final void enterChain(ChainContext ctx) {
    Automaton a = get(Automata.class).get(ctx);
    if (a != null) {
      analyze(ctx, a);
    }
  }
}
