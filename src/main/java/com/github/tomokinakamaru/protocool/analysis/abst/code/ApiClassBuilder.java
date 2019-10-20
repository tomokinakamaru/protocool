package com.github.tomokinakamaru.protocool.analysis.abst.code;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;

public abstract class ApiClassBuilder extends StateAnalyzer {

  protected ClassContext context;

  protected State state;

  protected abstract boolean skip();

  protected abstract void prepare();

  protected abstract void build();

  @Override
  protected final void analyze(ClassContext ctx, Automaton a, State s) {
    this.context = ctx;
    this.state = s;
    if (!skip()) {
      prepare();
      build();
    }
  }

  @Override
  protected final void analyze(ChainContext ctx, Automaton a, State s) {}
}
