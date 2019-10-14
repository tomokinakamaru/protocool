package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;

public class BuildMethodNodeBaseName extends Analysis {

  @Override
  public void enterMethod(MethodContext ctx) {
    StringBuilder builder = new StringBuilder();
    builder.append(ctx.name().getText());
    for (ArgumentContext c : ctx.argument()) {
      builder.append("$").append(c.name().getText());
    }
    ctx.nodeBaseName = builder.toString();
  }
}
