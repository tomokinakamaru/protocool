package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.AutomatonAnalysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Parameters;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Symbol;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import java.util.ArrayList;
import java.util.List;

public class PropagateParameter extends AutomatonAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton) {
    List<State> queue = new ArrayList<>();
    State initial = automaton.initials.stream().findAny().orElseThrow(RuntimeException::new);

    initial.parameters = new Parameters(ctx.head().parameter());
    queue.add(initial);

    while (!queue.isEmpty()) {
      State src = queue.remove(0);
      for (Transition transition : automaton.getTransitionsFrom(src)) {
        Symbol symbol = transition.symbol;
        if (symbol.hasReferenceContext()) {
          continue;
        }

        State dst = transition.destination;
        Parameters srcParams = src.parameters.copy();
        Parameters symParams = symbol.getMethodContext().parameters.copy();
        Parameters srcSymParams = srcParams.union(symParams);

        if (dst.parameters != null) {
          if (!dst.parameters.equals(srcSymParams)) {
            if (symParams.isEmpty()) {
              dst.parameters = srcSymParams;
            } else {
              State state = clone(automaton, dst);
              state.parameters = srcSymParams;
              automaton.transitions.add(new Transition(src, symbol, state));
              automaton.transitions.remove(transition);
              queue.add(state);
            }
          }
        } else {
          dst.parameters = srcSymParams;
          queue.add(dst);
        }
      }
    }
  }

  private static State clone(Automaton automaton, State state) {
    State s = new State();
    for (Transition t : automaton.getTransitionsFrom(state)) {
      State dst = t.destination;
      if (dst.equals(t.source)) {
        dst = s;
      }
      automaton.transitions.add(new Transition(s, t.symbol, dst));
    }
    return s;
  }
}
