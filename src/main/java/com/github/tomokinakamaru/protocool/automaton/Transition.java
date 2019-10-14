package com.github.tomokinakamaru.protocool.automaton;

import com.github.tomokinakamaru.fsa.AbstractTransition;

public class Transition extends AbstractTransition<State, Symbol> {

  public Transition(State source, Symbol symbol, State destination) {
    super(source, symbol, destination);
  }
}
