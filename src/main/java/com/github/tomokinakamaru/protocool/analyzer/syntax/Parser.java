package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.TokenStream;

public class Parser extends GrammarParser {

  public Parser(TokenStream input) {
    super(input);
    removeErrorListener(ConsoleErrorListener.INSTANCE);
    addErrorListener(ErrorListener.INSTANCE);
  }
}
