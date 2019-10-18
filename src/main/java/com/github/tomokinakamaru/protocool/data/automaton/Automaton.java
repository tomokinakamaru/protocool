package com.github.tomokinakamaru.protocool.data.automaton;

import com.github.tomokinakamaru.fsa.AbstractAutomaton;
import java.util.Set;

public class Automaton extends AbstractAutomaton<State, Symbol, Transition, Automaton> {

  public Automaton() {}

  public Automaton(Symbol symbol) {
    super(symbol);
  }

  public static Automaton empty() {
    Automaton automaton = new Automaton();
    automaton.initials.add(new State());
    return automaton;
  }

  public Automaton minDet() {
    return repeated().determinized().repeated().determinized();
  }

  @Override
  protected State newState() {
    return new State();
  }

  @Override
  protected State newState(Set<State> states) {
    return newState();
  }

  @Override
  protected State newState(State state) {
    return newState();
  }

  @Override
  protected Transition newTransition(State source, Symbol symbol, State destination) {
    return new Transition(source, symbol, destination);
  }

  @Override
  protected Automaton newAutomaton() {
    return new Automaton();
  }
}
