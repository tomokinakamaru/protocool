package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automata;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;

public abstract class AutomatonAnalyzer extends Listener {

  protected void analyze(ClassContext ctx, Automaton a) {}

  protected void analyze(ExpressionContext ctx, Automaton a) {}

  @Override
  public final void enterClass(ClassContext ctx) {
    analyze(ctx, get(Automata.class).get(ctx));
  }

  @Override
  public final void enterExpression(ExpressionContext ctx) {
    analyze(ctx, get(Automata.class).get(ctx));
  }
}
