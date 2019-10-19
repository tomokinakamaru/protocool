package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.error.ReturnTypeConflict;
import java.util.Set;

public class ValidateReturnType extends StateAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a, State s) {
    Set<Transition> transitions = a.getTransitionsFrom(s);
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
