package com.github.tomokinakamaru.protocool.analysis.abst.code;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.tomokinakamaru.protocool.analysis.abst.automaton.TransitionAnalyzer;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.data.code.ApiClasses;

public abstract class ApiMethodBuilder extends TransitionAnalyzer {

  protected Transition transition;

  protected ClassOrInterfaceDeclaration classDecl;

  protected abstract void prepare();

  protected abstract void build();

  @Override
  protected final void analyze(ClassContext ctx, Automaton a, Transition t) {
    this.transition = t;
    this.classDecl = get(ApiClasses.class).get(t.source);
    prepare();
    build();
  }

  @Override
  protected final void analyze(ChainContext ctx, Automaton a, Transition t) {}
}
