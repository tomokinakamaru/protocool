package com.github.tomokinakamaru.protocool.analyzer;

import org.antlr.v4.runtime.ParserRuleContext;

public final class Utility {

  @SuppressWarnings("unchecked")
  public static <T extends ParserRuleContext> T findParent(Class<T> cls, ParserRuleContext ctx) {
    ParserRuleContext c = ctx;
    while (true) {
      if (cls.isInstance(c)) {
        return (T) c;
      }
      c = c.getParent();
      if (c == null) {
        return null;
      }
    }
  }
}
