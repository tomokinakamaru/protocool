package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;

public abstract class TransitionAnalyzer extends AutomatonAnalyzer {

  protected void analyze(ClassContext ctx, Automaton a, Transition t) {}

  protected void analyze(ChainContext ctx, Automaton a, Transition t) {}

  @Override
  protected final void analyze(ClassContext ctx, Automaton a) {
    a.transitions.forEach(t -> analyze(ctx, a, t));
  }

  @Override
  protected final void analyze(ChainContext ctx, Automaton a) {
    a.transitions.forEach(t -> analyze(ctx, a, t));
  }
}
