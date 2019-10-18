package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.error.BadSpecification;
import java.util.Set;

public class ValidateReturnType extends StateAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a, State s) {
    Set<Transition> transitions = a.getTransitionsFrom(s);
    if (1 < transitions.size()) {
      if (transitions.stream().anyMatch(t -> t.symbol.isReferenceContext())) {
        throw new BadSpecification();
      }
    }
  }
}
