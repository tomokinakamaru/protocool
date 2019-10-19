package com.github.tomokinakamaru.protocool.analysis.abst.code;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;

public abstract class StateClassBuilder extends StateAnalyzer {

  private ClassContext classContext;

  private State state;

  protected abstract void build();

  protected ClassContext getClassContext() {
    return classContext;
  }

  protected State getState() {
    return state;
  }

  @Override
  protected final void analyze(ClassContext ctx, Automaton a, State s) {
    classContext = ctx;
    state = s;
    build();
  }

  @Override
  protected final void analyze(ChainContext ctx, Automaton a, State s) {}
}
