package com.github.tomokinakamaru.protocool.data;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Symbol {

  public final ParserRuleContext context;

  public String normalForm;

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
    return normalForm.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Symbol)) {
      return false;
    }
    Symbol symbol = (Symbol) obj;
    return normalForm.equals(symbol.normalForm);
  }
}
