package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.BadSpecification;
import com.github.tomokinakamaru.protocool.StateAnalysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ReferenceContext;
import java.util.Set;
import java.util.stream.Collectors;

public class BackPropagateParameter extends StateAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton, State state) {
    if (state.context == null) {
      return;
    }

    ReferenceContext c = state.context;
    if (state.parameters.containsAll(c.parameters)) {
      return;
    }

    Set<Transition> ts = automaton.getTransitionsTo(state);
    Set<State> sources = ts.stream().map(t -> t.source).collect(Collectors.toSet());
    if (!automaton.initials.containsAll(sources)) {
      throw new BadSpecification();
    }

    state.parameters = c.parameters.copy();
  }
}
