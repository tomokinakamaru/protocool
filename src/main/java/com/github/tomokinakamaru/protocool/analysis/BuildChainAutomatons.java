package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ElementContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.FactorContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.TermContext;
import com.github.tomokinakamaru.protocool.context.Automatons;
import com.github.tomokinakamaru.protocool.context.NormalForms;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Symbol;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;

public class BuildChainAutomatons extends FileContextAnalysis {

  @Override
  public void initialize() {
    set(new Automatons());
  }

  @Override
  public void enterChain(ChainContext ctx) {
    get(Automatons.class).put(ctx, create(ctx));
  }

  private Automaton create(ChainContext ctx) {
    return create(ctx.expression()).and(create(ctx.reference())).minDeterminized();
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
    Symbol symbol = new Symbol(ctx);
    symbol.normalForm = get(NormalForms.class).get(ctx);
    return new Automaton(symbol);
  }

  private Automaton create(ReferenceContext ctx) {
    Symbol symbol = new Symbol(ctx);
    symbol.normalForm = get(NormalForms.class).get(ctx);
    return new Automaton(symbol);
  }
}
