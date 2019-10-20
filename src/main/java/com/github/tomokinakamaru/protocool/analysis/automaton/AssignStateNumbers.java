package com.github.tomokinakamaru.protocool.analysis.automaton;

import static com.github.tomokinakamaru.protocool.data.automaton.State.INITIAL_NUMBER;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AssignStateNumbers extends AutomatonAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a) {
    List<State> queue = new ArrayList<>(a.initials);
    Set<State> queuedStates = new HashSet<>(a.initials);

    int n = INITIAL_NUMBER;
    while (!queue.isEmpty()) {
      State source = queue.remove(0);
      if (source.context == null) {
        source.number = n;
        n += 1;
      }
      for (Transition transition : a.transitions) {
        if (!queuedStates.contains(transition.destination)) {
          queue.add(transition.destination);
          queuedStates.add(transition.destination);
        }
      }
    }
  }
}
