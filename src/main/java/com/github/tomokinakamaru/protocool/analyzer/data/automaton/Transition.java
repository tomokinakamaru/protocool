package com.github.tomokinakamaru.protocool.analyzer.data.automaton;

import com.github.tomokinakamaru.fsa.AbstractTransition;

public class Transition extends AbstractTransition<State, Symbol> {

  protected Transition(State source, Symbol symbol, State destination) {
    super(source, symbol, destination);
  }
}
