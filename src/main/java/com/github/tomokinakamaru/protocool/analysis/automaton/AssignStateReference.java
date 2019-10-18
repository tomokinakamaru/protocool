package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.TransitionAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;

public class AssignStateReference extends TransitionAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a, Transition t) {
    if (t.symbol.isReferenceContext()) {
      t.source.context = t.symbol.asReferenceContext();
    }
  }
}
