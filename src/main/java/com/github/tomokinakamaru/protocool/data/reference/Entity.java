package com.github.tomokinakamaru.protocool.data.reference;

import org.antlr.v4.runtime.ParserRuleContext;

public class Entity {

  public final ParserRuleContext context;

  Entity(ParserRuleContext context) {
    this.context = context;
  }
}
