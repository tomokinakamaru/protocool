package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Symbol;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;

public class BuildClazzAutomaton extends Analysis {

  @Override
  public void enterClazz(ClazzContext ctx) {
    ctx.automaton =
        ctx.body()
            .chain()
            .stream()
            .map(BuildClazzAutomaton::create)
            .reduce(Automaton::or)
            .map(BuildClazzAutomaton::determinize)
            .orElse(emptyAutomaton());
  }

  private static Automaton create(ChainContext ctx) {
    return ctx.expression().automaton.and(new Automaton(new Symbol(ctx.reference())));
  }

  private static Automaton determinize(Automaton automaton) {
    return automaton.repeated().determinized().repeated().determinized();
  }

  private static Automaton emptyAutomaton() {
    Automaton automaton = new Automaton();
    State state = new State();
    automaton.initials.add(state);
    return automaton;
  }
}