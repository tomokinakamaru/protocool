package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Symbol;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ElementContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.FactorContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.TermContext;

public class BuildExpressionAutomaton extends Analysis {

  @Override
  public void enterExpression(ExpressionContext ctx) {
    ctx.automaton = create(ctx);
  }

  private static Automaton create(ExpressionContext ctx) {
    return ctx.term()
        .stream()
        .map(BuildExpressionAutomaton::create)
        .reduce(Automaton::or)
        .orElseThrow(RuntimeException::new);
  }

  private static Automaton create(TermContext ctx) {
    return ctx.factor()
        .stream()
        .map(BuildExpressionAutomaton::create)
        .reduce(Automaton::and)
        .orElseThrow(RuntimeException::new);
  }

  private static Automaton create(FactorContext ctx) {
    Automaton automaton = create(ctx.element());
    if (ctx.OPTIONAL() != null) {
      return automaton.or(new Automaton(null));
    }
    if (ctx.REPEAT0() != null) {
      return automaton.repeated();
    }
    if (ctx.REPEAT1() != null) {
      return automaton.and(automaton.repeated());
    }
    return automaton;
  }

  private static Automaton create(ElementContext ctx) {
    return ctx.method() == null ? create(ctx.expression()) : create(ctx.method());
  }

  private static Automaton create(MethodContext ctx) {
    return new Automaton(new Symbol(ctx));
  }
}
