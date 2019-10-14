package com.github.tomokinakamaru.protocool.analysis.automaton;

import static com.github.tomokinakamaru.protocool.data.automaton.State.INITIAL_NUMBER;

import com.github.tomokinakamaru.protocool.analysis.AutomatonAnalysis;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AssignStateNumber extends AutomatonAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton) {
    int n = INITIAL_NUMBER;

    List<State> queue = new ArrayList<>(automaton.initials);
    Set<State> queuedStates = new HashSet<>(automaton.initials);

    while (!queue.isEmpty()) {
      State source = queue.remove(0);
      source.number = n;
      n += 1;
      for (Transition transition : automaton.transitions) {
        if (!queuedStates.contains(transition.destination)) {
          queue.add(transition.destination);
          queuedStates.add(transition.destination);
        }
      }
    }
  }
}
