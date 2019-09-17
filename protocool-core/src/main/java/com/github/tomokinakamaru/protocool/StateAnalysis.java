package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ExpressionContext;

public abstract class StateAnalysis extends AutomatonAnalysis {

  protected void process(ClazzContext ctx, Automaton automaton, State state) {}

  protected void process(ExpressionContext ctx, Automaton automaton, State state) {}

  @Override
  protected final void process(ClazzContext ctx, Automaton automaton) {
    automaton.getStates().forEach(s -> process(ctx, automaton, s));
  }

  @Override
  protected final void process(ExpressionContext ctx, Automaton automaton) {
    automaton.getStates().forEach(s -> process(ctx, automaton, s));
  }
}
