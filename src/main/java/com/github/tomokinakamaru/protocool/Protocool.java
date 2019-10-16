package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.analyzer.Analyzer;
import com.github.tomokinakamaru.protocool.analyzer.reference.TableBuilder;
import com.github.tomokinakamaru.protocool.analyzer.syntax.SyntaxAnalyzer;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;

public class Protocool {

  public final List<Analyzer> analyzers = defaultAnalyzers();

  public void run(CharStream charStream) {
    Context context = new Context();
    context.charStream = charStream;
    analyzers.forEach(a -> a.context = context);
    analyzers.forEach(Analyzer::run);
  }

  private static List<Analyzer> defaultAnalyzers() {
    return Arrays.asList(new SyntaxAnalyzer(), new TableBuilder());
  }
}
