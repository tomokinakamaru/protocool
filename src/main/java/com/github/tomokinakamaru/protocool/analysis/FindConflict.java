package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.BadSpecification;
import com.github.tomokinakamaru.protocool.StateAnalysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;
import java.util.Set;

public class FindConflict extends StateAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton, State state) {
    Set<Transition> transitions = automaton.getTransitionsFrom(state);
    for (Transition t1 : transitions) {
      String s1 = t1.symbol.getMethodContext().signature;
      for (Transition t2 : transitions) {
        String s2 = t2.symbol.getMethodContext().signature;
        if (t1 != t2 && s1.equals(s2)) {
          throw new BadSpecification();
        }
      }
    }
  }
}