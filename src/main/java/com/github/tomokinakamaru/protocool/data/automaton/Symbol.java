package com.github.tomokinakamaru.protocool.data.automaton;

import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ReferenceContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Symbol {

  private ParserRuleContext context;

  public String normalizedText;

  public Symbol(ParserRuleContext ctx) {
    this.context = ctx;
  }

  public boolean isMethodContext() {
    return context instanceof MethodContext;
  }

  public boolean isReferenceContext() {
    return context instanceof ReferenceContext;
  }

  public MethodContext asMethodContext() {
    return (MethodContext) context;
  }

  public ReferenceContext asReferenceContext() {
    return (ReferenceContext) context;
  }

  @Override
  public int hashCode() {
    return normalizedText.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Symbol)) {
      return false;
    }
    Symbol symbol = (Symbol) obj;
    return normalizedText.equals(symbol.normalizedText);
  }
}
