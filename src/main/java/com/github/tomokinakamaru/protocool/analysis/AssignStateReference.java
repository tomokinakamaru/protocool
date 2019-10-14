package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.TransitionAnalysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.exception.BadSpecification;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;

public class AssignStateReference extends TransitionAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton, Transition transition) {
    if (transition.symbol.hasReferenceContext()) {
      if (automaton.getTransitionsFrom(transition.source).size() != 1) {
        throw new BadSpecification();
      }
      transition.source.context = transition.symbol.getReferenceContext();
    }
  }
}
