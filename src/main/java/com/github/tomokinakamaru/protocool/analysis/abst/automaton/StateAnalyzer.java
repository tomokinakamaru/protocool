package com.github.tomokinakamaru.protocool.analysis.abst.automaton;

import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;

public abstract class StateAnalyzer extends AutomatonAnalyzer {

  protected void analyze(ClassContext ctx, Automaton a, State s) {}

  protected void analyze(ExpressionContext ctx, Automaton a, State s) {}

  @Override
  protected final void analyze(ClassContext ctx, Automaton a) {
    a.getStates().forEach(s -> analyze(ctx, a, s));
  }

  @Override
  protected void analyze(ExpressionContext ctx, Automaton a) {
    a.getStates().forEach(s -> analyze(ctx, a, s));
  }
}
