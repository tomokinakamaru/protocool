package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.code.ApiClass;
import com.github.tomokinakamaru.protocool.data.code.ApiClasses;

public class EncodeState extends StateAnalyzer {

  @Override
  public void initialize() {
    set(new ApiClasses());
  }

  @Override
  protected void analyze(ClassContext ctx, Automaton a, State s) {
    get(ApiClasses.class).put(s, new ApiClass());
  }
}
