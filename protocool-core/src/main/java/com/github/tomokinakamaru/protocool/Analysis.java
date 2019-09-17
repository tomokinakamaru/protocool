package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.parser.SpecificationBaseListener;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.SpecificationContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public abstract class Analysis extends SpecificationBaseListener {

  public final void run(SpecificationContext ctx) {
    ParseTreeWalker.DEFAULT.walk(this, ctx);
  }
}
