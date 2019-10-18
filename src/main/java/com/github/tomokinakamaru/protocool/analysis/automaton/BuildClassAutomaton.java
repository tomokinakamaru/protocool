package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automata;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.Symbol;

public class BuildClassAutomaton extends Listener {

  @Override
  public void enterClass(ClassContext ctx) {
    get(Automata.class).put(ctx, create(ctx));
  }

  private Automaton create(ClassContext ctx) {
    return ctx.chain()
        .stream()
        .map(this::create)
        .reduce(Automaton::or)
        .map(Automaton::minDet)
        .orElse(Automaton.empty());
  }

  private Automaton create(ChainContext ctx) {
    return get(Automata.class).get(ctx.expression()).and(create(ctx.reference()));
  }

  private Automaton create(ReferenceContext ctx) {
    return new Automaton(new Symbol(ctx));
  }
}
