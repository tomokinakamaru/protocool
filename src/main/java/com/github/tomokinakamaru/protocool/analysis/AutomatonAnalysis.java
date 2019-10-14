package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.automaton.Automaton;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ExpressionContext;

public abstract class AutomatonAnalysis extends Analysis {

  protected void process(ClazzContext ctx, Automaton automaton) {}

  protected void process(ExpressionContext ctx, Automaton automaton) {}

  @Override
  public final void enterClazz(ClazzContext ctx) {
    process(ctx, ctx.automaton);
  }

  @Override
  public final void enterExpression(ExpressionContext ctx) {
    process(ctx, ctx.automaton);
  }
}
