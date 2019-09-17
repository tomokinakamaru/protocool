package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ExpressionContext;

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
