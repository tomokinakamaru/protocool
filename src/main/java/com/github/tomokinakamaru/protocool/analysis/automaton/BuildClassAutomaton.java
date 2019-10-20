package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.BodyContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.NormalForms;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Automatons;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Symbol;

public class BuildClassAutomaton extends Listener {

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
    return get(Automatons.class).get(ctx).and(create(ctx.reference()));
  }

  private Automaton create(ReferenceContext ctx) {
    return new Automaton(new Symbol(ctx, get(NormalForms.class)));
  }

  private Automaton create() {
    Automaton automaton = new Automaton();
    automaton.initials.add(new State());
    return automaton;
  }
}
