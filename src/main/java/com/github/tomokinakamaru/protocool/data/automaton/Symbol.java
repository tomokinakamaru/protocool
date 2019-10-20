package com.github.tomokinakamaru.protocool.data.automaton;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.NormalForms;
import org.antlr.v4.runtime.ParserRuleContext;

public class Symbol {

  private final ParserRuleContext context;

  private final NormalForms normalForms;

  public Symbol(ParserRuleContext ctx, NormalForms normalForms) {
    this.context = ctx;
    this.normalForms = normalForms;
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
    return normalForms.get(context).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Symbol)) {
      return false;
    }
    Symbol symbol = (Symbol) obj;
    return normalForms.get(context).equals(normalForms.get(symbol.context));
  }
}
