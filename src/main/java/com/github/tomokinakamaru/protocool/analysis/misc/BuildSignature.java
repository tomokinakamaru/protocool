package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;

public class BuildSignature extends Analysis {

  @Override
  public void exitMethod(MethodContext ctx) {
    StringBuilder builder = new StringBuilder();

    builder.append(ctx.name().getText());
    builder.append("(");
    for (int i = 0; i < ctx.argument().size(); i++) {
      builder.append(ctx.argument(i).signature);
      if (i < ctx.argument().size() - 1) {
        builder.append(", ");
      }
    }
    builder.append(")");

    ctx.signature = builder.toString();
  }

  @Override
  public void exitArgument(ArgumentContext ctx) {
    StringBuilder builder = new StringBuilder();

    builder.append(ctx.reference().signature);
    if (ctx.ELLIPSIS() != null) {
      builder.append("[]");
    }

    ctx.signature = builder.toString();
  }

  @Override
  public void exitReference(ReferenceContext ctx) {
    StringBuilder builder = new StringBuilder();

    if (ctx.parameterDestination == null) {
      builder.append(ctx.qualifiedName().getText());
    } else {
      builder.append("*");
    }

    for (int i = 0; i < ctx.ARRAY().size(); i++) {
      builder.append("[]");
    }

    ctx.signature = builder.toString();
  }
}
