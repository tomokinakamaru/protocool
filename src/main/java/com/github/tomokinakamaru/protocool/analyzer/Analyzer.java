package com.github.tomokinakamaru.protocool.analyzer;

import com.github.tomokinakamaru.protocool.CompileContext;

public abstract class Analyzer {

  public abstract void run();

  public CompileContext context;
}
