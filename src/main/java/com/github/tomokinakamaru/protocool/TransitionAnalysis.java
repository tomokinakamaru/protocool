package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ExpressionContext;

public abstract class TransitionAnalysis extends AutomatonAnalysis {

  protected void process(ClazzContext ctx, Automaton automaton, Transition transition) {}

  protected void process(ExpressionContext ctx, Automaton automaton, Transition transition) {}

  @Override
  protected final void process(ClazzContext ctx, Automaton automaton) {
    automaton.transitions.forEach(t -> process(ctx, automaton, t));
  }

  @Override
  protected final void process(ExpressionContext ctx, Automaton automaton) {
    automaton.transitions.forEach(t -> process(ctx, automaton, t));
  }
}
