package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.antlr4.utility.AbstractParser;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarLexer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser;
import com.github.tomokinakamaru.protocool.error.ParseError;
import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;

public class Parse extends AbstractParser<GrammarParser> {

  @Override
  protected Function<CharStream, ? extends Lexer> newLexer() {
    return GrammarLexer::new;
  }

  @Override
  protected Function<TokenStream, GrammarParser> newParser() {
    return GrammarParser::new;
  }

  @Override
  protected Function<GrammarParser, ? extends ParserRuleContext> getRootContext() {
    return GrammarParser::file;
  }

  @Override
  public void syntaxError(Recognizer r, Object o, int l, int c, String m, RecognitionException e) {
    throw new ParseError(l, c, m);
  }
}
