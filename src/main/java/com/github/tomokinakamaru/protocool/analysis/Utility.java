package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import org.antlr.v4.runtime.ParserRuleContext;

public final class Utility {

  private Utility() {}

  public static ClassContext findClassContext(ParserRuleContext ctx) {
    while (ctx != null) {
      if (ctx instanceof ClassContext) {
        return (ClassContext) ctx;
      }
      ctx = ctx.getParent();
    }
    throw new RuntimeException();
  }
}
