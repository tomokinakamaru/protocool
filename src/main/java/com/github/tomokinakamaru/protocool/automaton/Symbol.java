package com.github.tomokinakamaru.protocool.automaton;

import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Symbol {

  private final ParserRuleContext context;

  public Symbol(MethodContext context) {
    this.context = context;
  }

  public Symbol(ReferenceContext context) {
    this.context = context;
  }

  public boolean hasMethodContext() {
    return context instanceof MethodContext;
  }

  public boolean hasReferenceContext() {
    return context instanceof ReferenceContext;
  }

  public MethodContext getMethodContext() {
    return (MethodContext) context;
  }

  public ReferenceContext getReferenceContext() {
    return (ReferenceContext) context;
  }

  @Override
  public int hashCode() {
    return getText().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Symbol)) {
      return false;
    }
    Symbol symbol = (Symbol) obj;
    return getText().equals(symbol.getText());
  }

  private String getText() {
    if (hasReferenceContext()) {
      return getReferenceContext().normalizedText;
    }

    StringBuilder builder = new StringBuilder();
    if (getMethodContext().isStatic) {
      builder.append("static ");
    }
    builder.append(getMethodContext().normalizedText);
    if (getMethodContext().evaluator != null) {
      builder.append(" by ").append(getMethodContext().evaluator);
    }
    return builder.toString();
  }
}
