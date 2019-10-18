package com.github.tomokinakamaru.protocool.analyzer;

import com.github.tomokinakamaru.antlr4utilities.AbstractParser;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationLexer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser;
import java.util.function.Function;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;

public class Parser extends AbstractParser<SpecificationParser> {

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
    return SpecificationParser::specification;
  }
}
