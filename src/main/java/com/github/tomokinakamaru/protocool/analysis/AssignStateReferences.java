package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.utility.TransitionAnalysis;

public class AssignStateReferences extends TransitionAnalysis {

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton, Transition transition) {
    if (transition.symbol.isReferenceContext()) {
      transition.source.context = transition.symbol.asReferenceContext();
    }
  }
}
