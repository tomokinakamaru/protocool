package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

public class Lexer extends GrammarLexer {

  public Lexer(CharStream input) {
    super(input);
    removeErrorListener(ConsoleErrorListener.INSTANCE);
    addErrorListener(ErrorListener.INSTANCE);
  }
}
