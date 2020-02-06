package com.github.tomokinakamaru.protocool.analysis;

import static com.github.tomokinakamaru.protocool.data.State.INITIAL_NUMBER;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.utility.AutomatonAnalysis;
import com.github.tomokinakamaru.protocool.utility.MemoryQueue;
import java.util.Queue;

public class AssignStateNumbers extends AutomatonAnalysis {

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton) {
    Queue<State> queue = new MemoryQueue<>(automaton.initials);
    int n = INITIAL_NUMBER;
    while (!queue.isEmpty()) {
      State source = queue.remove();
      if (source.context == null) {
        source.number = n++;
      }
      automaton.transitions.forEach(t -> queue.add(t.destination));
    }
  }
}
