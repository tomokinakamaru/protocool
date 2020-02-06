package com.github.tomokinakamaru.protocool.data;

import com.github.tomokinakamaru.fsa.StateInterface;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import java.util.Set;

public class State implements StateInterface {

  public static final int INITIAL_NUMBER = 0;

  public int number;

  public ReferenceContext context;

  public Set<ParameterContext> parameters;
}
