package com.github.tomokinakamaru.protocool.analysis.abst.tree;

import com.github.tomokinakamaru.antlr4.utility.AbstractVisitor;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.FileContext;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Visitor<T> extends AbstractVisitor<T> implements DefaultVisitor<T> {

  @Override
  protected ParserRuleContext getContext() {
    return get(FileContext.class);
  }
}
