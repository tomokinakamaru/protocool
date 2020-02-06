package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.context.NormalForms;
import com.github.tomokinakamaru.protocool.context.ReturnExpressions;
import com.github.tomokinakamaru.protocool.context.StaticMethods;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;

public class RebuildNormalForms extends FileContextAnalysis {

  @Override
  public void enterMethod(MethodContext ctx) {
    StringBuilder builder = new StringBuilder();

    if (get(StaticMethods.class).contains(ctx)) {
      builder.append("static").append(" ");
    }

    builder.append(get(NormalForms.class).get(ctx));

    if (get(ReturnExpressions.class).get(ctx) != null) {
      builder
          .append(" ")
          .append("return")
          .append(" ")
          .append(get(ReturnExpressions.class).get(ctx));
    }

    get(NormalForms.class).put(ctx, builder.toString());
  }
}
