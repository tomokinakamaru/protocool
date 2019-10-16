package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

public class Lexer extends SpecificationLexer {

  public Lexer(CharStream input) {
    super(input);
    removeErrorListener(ConsoleErrorListener.INSTANCE);
    addErrorListener(new ErrorListener());
  }
}
