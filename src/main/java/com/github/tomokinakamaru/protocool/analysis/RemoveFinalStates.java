package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.utility.AutomatonAnalysis;

public class RemoveFinalStates extends AutomatonAnalysis {

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton) {
    automaton.transitions.removeAll(automaton.getTransitionsTo(automaton.finals));
    automaton.finals.clear();
  }
}
