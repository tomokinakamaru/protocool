package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.BodyContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.context.Automatons;
import com.github.tomokinakamaru.protocool.context.NormalForms;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Symbol;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;

public class BuildClassAutomatons extends FileContextAnalysis {

  @Override
  public void enterClass(ClassContext ctx) {
    get(Automatons.class).put(ctx, create(ctx));
  }

  private Automaton create(ClassContext ctx) {
    return create(ctx.body());
  }

  private Automaton create(BodyContext ctx) {
    return ctx.chain()
        .stream()
        .map(this::create)
        .reduce(Automaton::or)
        .map(Automaton::minDeterminized)
        .orElse(this.create());
  }

  private Automaton create(ChainContext ctx) {
    Automaton automaton = get(Automatons.class).get(ctx);
    for (Transition t : automaton.transitions) {
      t.symbol.normalForm = get(NormalForms.class).get(t.symbol.context);
    }
    return automaton;
  }

  private Automaton create(ReferenceContext ctx) {
    Symbol symbol = new Symbol(ctx);
    symbol.normalForm = get(NormalForms.class).get(ctx);
    return new Automaton(symbol);
  }

  private Automaton create() {
    Automaton automaton = new Automaton();
    automaton.initials.add(new State());
    return automaton;
  }
}
