package com.github.tomokinakamaru.protocool.analyzer.automaton;

import com.github.tomokinakamaru.protocool.analyzer.Listener;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;

public class ClassAutomatonBuilder extends Listener {

  @Override
  public void enterClass(ClassContext ctx) {
    // context.automata.put(ctx, create(ctx));
  }

  private Automaton create(ClassContext ctx) {
    return ctx.chain()
        .stream()
        .map(this::create)
        .reduce(Automaton::or)
        .map(this::determinize)
        .orElse(emptyAutomaton());
  }

  private Automaton create(ChainContext ctx) {
    return null; // context.automata.get(ctx.expression()).and(new Automaton(new
    // Symbol(ctx.reference())));
  }

  private Automaton determinize(Automaton automaton) {
    return automaton.repeated().determinized().repeated().determinized();
  }

  private Automaton emptyAutomaton() {
    Automaton automaton = new Automaton();
    State state = new State();
    automaton.initials.add(state);
    return automaton;
  }
}
