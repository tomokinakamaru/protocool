package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4.utility.AbstractCompiler;
import com.github.tomokinakamaru.antlr4.utility.Context;
import com.github.tomokinakamaru.protocool.analysis.BuildTypeTable;
import com.github.tomokinakamaru.protocool.analysis.Parse;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildClassAutomaton;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildExpressionAutomaton;
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
    return Arrays.asList(
        new Parse(),
        new BuildTypeTable(),
        new BuildExpressionAutomaton(),
        new BuildClassAutomaton());
  }
}
