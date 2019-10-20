package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.TransitionAnalyzer;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;

public class AssignStateReferences extends TransitionAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a, Transition t) {
    if (t.symbol.isReferenceContext()) {
      t.source.context = t.symbol.asReferenceContext();
    }
  }
}
