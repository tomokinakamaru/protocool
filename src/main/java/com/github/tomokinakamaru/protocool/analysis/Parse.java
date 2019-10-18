package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.antlr4.utility.AbstractParser;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationLexer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser;
import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;

public class Parse extends AbstractParser<SpecificationParser> {

  @Override
  protected Function<CharStream, ? extends Lexer> newLexer() {
    return SpecificationLexer::new;
  }

  @Override
  protected Function<TokenStream, SpecificationParser> newParser() {
    return SpecificationParser::new;
  }

  @Override
  protected Function<SpecificationParser, ? extends ParserRuleContext> getRootContext() {
    return SpecificationParser::file;
  }
}
