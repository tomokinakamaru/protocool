package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.AutomatonAnalysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;

public class RemoveFinalStates extends AutomatonAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton) {
    automaton.transitions.removeAll(automaton.getTransitionsTo(automaton.finals));
    automaton.finals.clear();
  }
}
