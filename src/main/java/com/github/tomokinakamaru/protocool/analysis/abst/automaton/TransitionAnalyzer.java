package com.github.tomokinakamaru.protocool.analysis.abst.automaton;

import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;

public abstract class TransitionAnalyzer extends AutomatonAnalyzer {

  protected void analyze(ClassContext ctx, Automaton a, Transition t) {}

  protected void analyze(ExpressionContext ctx, Automaton a, Transition t) {}

  @Override
  protected final void analyze(ClassContext ctx, Automaton a) {
    a.transitions.forEach(t -> analyze(ctx, a, t));
  }

  @Override
  protected final void analyze(ExpressionContext ctx, Automaton a) {
    a.transitions.forEach(t -> analyze(ctx, a, t));
  }
}
