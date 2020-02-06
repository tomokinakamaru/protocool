package com.github.tomokinakamaru.protocool.utility;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;

public abstract class StateAnalysis extends AutomatonAnalysis {

  protected void analyze(ClassContext ctx, Automaton automaton, State state) {}

  protected void analyze(ChainContext ctx, Automaton automaton, State state) {}

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton) {
    automaton.getStates().forEach(s -> analyze(ctx, automaton, s));
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton automaton) {
    automaton.getStates().forEach(s -> analyze(ctx, automaton, s));
  }
}
