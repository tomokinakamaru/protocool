package com.github.tomokinakamaru.protocool.analysis.abst.tree;

import com.github.tomokinakamaru.antlr4.utility.AbstractStreamVisitor;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.FileContext;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class StreamVisitor<T> extends AbstractStreamVisitor<T>
    implements DefaultVisitor<Stream<T>> {

  @Override
  protected ParserRuleContext getContext() {
    return get(FileContext.class);
  }
}
