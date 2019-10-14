package com.github.tomokinakamaru.protocool.data.automaton;

import com.github.tomokinakamaru.fsa.StateInterface;
import com.github.tomokinakamaru.protocool.analysis.data.Parameters;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;

public class State implements StateInterface {

  public static final int INITIAL_NUMBER = 0;

  public int number;

  public ReferenceContext context;

  public Parameters parameters;

  public boolean isInitial() {
    return number == INITIAL_NUMBER;
  }
}