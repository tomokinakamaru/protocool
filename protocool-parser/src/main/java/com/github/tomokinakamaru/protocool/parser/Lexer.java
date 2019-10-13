package com.github.tomokinakamaru.protocool.parser;

import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

public class Lexer extends SpecificationLexer {

  public Lexer(CharStream input) {
    super(input);
    removeErrorListener(ConsoleErrorListener.INSTANCE);
    addErrorListener(new ErrorListener());
  }
}
