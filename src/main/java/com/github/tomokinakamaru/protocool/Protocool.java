package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.antlr4utilities.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4utilities.AbstractCompiler;
import com.github.tomokinakamaru.antlr4utilities.Context;
import com.github.tomokinakamaru.protocool.analyzer.Parser;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;

public class Protocool extends AbstractCompiler {

  public void run(CharStream charStream) {
    Context context = new Context();
    context.set(charStream);
    compile(context);
  }

  @Override
  protected List<AbstractAnalyzer> analyses() {
    return Arrays.asList(new Parser());
  }
}
