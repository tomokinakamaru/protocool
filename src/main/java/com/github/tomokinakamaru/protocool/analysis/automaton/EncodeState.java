package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.javaparser.ast.CompilationUnit;
import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.StateClasses;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;

public class EncodeState extends StateAnalyzer {

  @Override
  public void initialize() {
    set(new StateClasses());
  }

  @Override
  protected void analyze(ClassContext ctx, Automaton a, State s) {
    CompilationUnit unit = new CompilationUnit();
    unit.addClass("State" + s.number);
    get(StateClasses.class).put(s, unit);
  }
}
