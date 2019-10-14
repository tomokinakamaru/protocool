package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationBaseListener;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public abstract class Analysis extends SpecificationBaseListener {

  public final void run(SpecificationContext ctx) {
    ParseTreeWalker.DEFAULT.walk(this, ctx);
  }
}
