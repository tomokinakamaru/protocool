package com.github.tomokinakamaru.protocool.utility;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;

public abstract class TransitionAnalysis extends AutomatonAnalysis {

  protected void analyze(ClassContext ctx, Automaton automaton, Transition transition) {}

  protected void analyze(ChainContext ctx, Automaton automaton, Transition transition) {}

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton) {
    automaton.transitions.forEach(t -> analyze(ctx, automaton, t));
  }

  @Override
  protected void analyze(ChainContext ctx, Automaton automaton) {
    automaton.transitions.forEach(t -> analyze(ctx, automaton, t));
  }
}
