package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.analyzer.Analyzer;
import org.antlr.v4.runtime.CommonTokenStream;

public class SyntaxAnalyzer extends Analyzer {

  @Override
  public void run() {
    context.fileContext = new Parser(new CommonTokenStream(new Lexer(context.charStream))).file();
  }
}
