package com.github.tomokinakamaru.protocool.analyzer.automaton;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Symbol;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.ElementContext;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.FactorContext;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.TermContext;

public class ChainAutomatonBuilder extends TreeAnalyzer {

  @Override
  public void enterExpression(ExpressionContext ctx) {
    context.automata.put(ctx, create(ctx));
  }

  private Automaton create(ExpressionContext ctx) {
    return ctx.term()
        .stream()
        .map(this::create)
        .reduce(Automaton::or)
        .orElseThrow(RuntimeException::new);
  }

  private Automaton create(TermContext ctx) {
    return ctx.factor()
        .stream()
        .map(this::create)
        .reduce(Automaton::and)
        .orElseThrow(RuntimeException::new);
  }

  private Automaton create(FactorContext ctx) {
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

  private Automaton create(ElementContext ctx) {
    return ctx.method() == null ? create(ctx.expression()) : create(ctx.method());
  }

  private Automaton create(MethodContext ctx) {
    return new Automaton(new Symbol(ctx));
  }
}
