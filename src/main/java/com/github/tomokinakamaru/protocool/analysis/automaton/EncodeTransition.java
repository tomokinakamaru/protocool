package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.TransitionAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.data.code.ApiMethod;
import com.github.tomokinakamaru.protocool.data.code.ApiMethods;

public class EncodeTransition extends TransitionAnalyzer {

  @Override
  public void initialize() {
    set(new ApiMethods());
  }

  @Override
  protected void analyze(ClassContext ctx, Automaton a, Transition t) {
    get(ApiMethods.class).put(t, new ApiMethod());
  }
}
