package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.error.ReturnTypeConflict;
import com.github.tomokinakamaru.protocool.utility.StateAnalysis;
import java.util.Set;

public class ValidateReturnTypes extends StateAnalysis {

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton, State state) {
    Set<Transition> transitions = automaton.getTransitionsFrom(state);
    if (1 < transitions.size()) {
      ReferenceContext c = findReferenceContext(transitions);
      if (c != null) {
        throw new ReturnTypeConflict(c.qualifiedName().getText());
      }
    }
  }

  private static ReferenceContext findReferenceContext(Set<Transition> transitions) {
    for (Transition t : transitions) {
      if (t.symbol.isReferenceContext()) {
        return t.symbol.asReferenceContext();
      }
    }
    return null;
  }
}
