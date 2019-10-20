package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;

public class RemoveFinalStates extends AutomatonAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a) {
    a.transitions.removeAll(a.getTransitionsTo(a.finals));
    a.finals.clear();
  }
}
