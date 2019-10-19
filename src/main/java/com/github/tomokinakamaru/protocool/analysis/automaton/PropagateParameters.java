package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Symbol;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PropagateParameters extends AutomatonAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a) {
    List<State> queue = new ArrayList<>();
    State initial = a.initials.stream().findAny().orElseThrow(RuntimeException::new);

    initial.parameters = new LinkedHashSet<>(ctx.head().parameter());
    queue.add(initial);

    while (!queue.isEmpty()) {
      State src = queue.remove(0);
      for (Transition transition : a.getTransitionsFrom(src)) {
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
              State state = clone(a, dst);
              state.parameters = srcSymParams;
              a.transitions.add(new Transition(src, symbol, state));
              a.transitions.remove(transition);
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
    CollectParameters collect = new CollectParameters(ctx);
    run(collect);
    return collect.parameters;
  }
}
