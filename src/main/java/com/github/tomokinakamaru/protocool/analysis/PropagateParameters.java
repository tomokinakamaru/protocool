package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Symbol;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.utility.AutomatonAnalysis;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PropagateParameters extends AutomatonAnalysis {

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton) {
    Queue<State> queue = new LinkedList<>();
    State initial = automaton.initials.stream().findAny().orElseThrow(RuntimeException::new);

    initial.parameters = new LinkedHashSet<>(ctx.head().parameter());
    queue.add(initial);

    while (!queue.isEmpty()) {
      State src = queue.remove();
      for (Transition transition : automaton.getTransitionsFrom(src)) {
        Symbol symbol = transition.symbol;
        if (symbol.isReferenceContext()) {
          continue;
        }

        Set<ParameterContext> srcParams = new LinkedHashSet<>(src.parameters);
        Set<ParameterContext> symParams = getParameters(symbol.asMethodContext());

        Set<ParameterContext> srcSymParams = new LinkedHashSet<>();
        srcSymParams.addAll(srcParams);
        srcSymParams.addAll(symParams);

        State dst = transition.destination;
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

  private State clone(Automaton automaton, State state) {
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

  private Set<ParameterContext> getParameters(MethodContext ctx) {
    return run(new CollectParameters(ctx)).parameters;
  }
}
