package com.github.tomokinakamaru.protocool.data;

import com.github.tomokinakamaru.fsa.AbstractAutomaton;

public class Automaton extends AbstractAutomaton<State, Symbol, Transition, Automaton> {

  public Automaton() {}

  public Automaton(Symbol symbol) {
    super(symbol);
  }

  @Override
  protected State newState() {
    return new State();
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
