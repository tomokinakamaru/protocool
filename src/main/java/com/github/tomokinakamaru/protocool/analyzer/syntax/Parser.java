package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.TokenStream;

public class Parser extends SpecificationParser {

  public Parser(TokenStream input) {
    super(input);
    removeErrorListener(ConsoleErrorListener.INSTANCE);
    addErrorListener(new ParserErrorListener());
  }
}
