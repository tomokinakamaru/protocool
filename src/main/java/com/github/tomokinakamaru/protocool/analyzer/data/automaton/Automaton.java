package com.github.tomokinakamaru.protocool.analyzer.data.automaton;

import com.github.tomokinakamaru.fsa.AbstractAutomaton;
import java.util.Set;

public class Automaton extends AbstractAutomaton<State, Symbol, Transition, Automaton> {

  @Override
  protected State newState() {
    return new State();
  }

  @Override
  protected State newState(Set<State> states) {
    return null;
  }

  @Override
  protected State newState(State state) {
    return null;
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
