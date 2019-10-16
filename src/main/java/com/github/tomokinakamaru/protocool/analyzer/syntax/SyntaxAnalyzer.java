package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.analyzer.Analyzer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class SyntaxAnalyzer extends Analyzer {

  @Override
  public void run() {
    Lexer lexer = new Lexer(context.charStream);
    TokenStream stream = new CommonTokenStream(lexer);
    Parser parser = new Parser(stream);
    context.specificationContext = parser.specification();
  }
}
