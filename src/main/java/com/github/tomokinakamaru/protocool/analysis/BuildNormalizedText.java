package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceOrWildcardContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.WildcardContext;

public class BuildNormalizedText extends Analysis {

  @Override
  public void exitMethod(MethodContext ctx) {
    StringBuilder builder = new StringBuilder();

    builder.append(ctx.name().getText());
    builder.append("(");
    for (int i = 0; i < ctx.argument().size(); i++) {
      builder.append(ctx.argument(i).normalizedText);
      if (i < ctx.argument().size() - 1) {
        builder.append(", ");
      }
    }
    builder.append(")");

    ctx.normalizedText = builder.toString();
  }

  @Override
  public void exitArgument(ArgumentContext ctx) {
    StringBuilder builder = new StringBuilder();

    builder.append(ctx.reference().normalizedText);
    if (ctx.ELLIPSIS() != null) {
      builder.append("[]");
    }
    builder.append(" ").append(ctx.name().getText());

    ctx.normalizedText = builder.toString();
  }

  @Override
  public void exitReference(ReferenceContext ctx) {
    StringBuilder builder = new StringBuilder();

    if (ctx.parameterDestination == null) {
      builder.append(ctx.qualifiedName().getText());
    } else {
      builder.append(ctx.parameterDestination.ownerClazz.head().name().getText());
      builder.append("$");
      builder.append(ctx.qualifiedName().getText());
    }

    if (!ctx.referenceOrWildcard().isEmpty()) {
      builder.append("<");
      for (int i = 0; i < ctx.referenceOrWildcard().size(); i++) {
        ReferenceOrWildcardContext c = ctx.referenceOrWildcard(i);
        if (c.reference() == null) {
          builder.append(c.wildcard().normalizedText);
        } else {
          builder.append(c.reference().normalizedText);
        }
        if (i < ctx.referenceOrWildcard().size() - 1) {
          builder.append(", ");
        }
      }
      builder.append(">");
    }

    for (int i = 0; i < ctx.ARRAY().size(); i++) {
      builder.append("[]");
    }

    ctx.normalizedText = builder.toString();
  }

  @Override
  public void exitWildcard(WildcardContext ctx) {
    StringBuilder builder = new StringBuilder("?");
    if (ctx.reference() != null) {
      if (ctx.SUPER() == null) {
        builder.append(" super ");
      } else {
        builder.append(" extends ");
      }
      builder.append(ctx.reference().normalizedText);
    }
    ctx.normalizedText = builder.toString();
  }
}
