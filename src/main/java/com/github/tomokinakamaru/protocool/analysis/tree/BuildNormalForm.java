package com.github.tomokinakamaru.protocool.analysis.tree;

import static com.github.tomokinakamaru.antlr4.utility.Stringifier.stringify;

import com.github.tomokinakamaru.protocool.analysis.abst.tree.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.NormalForms;
import com.github.tomokinakamaru.protocool.data.ReturnExpressions;
import com.github.tomokinakamaru.protocool.data.StaticMethods;

public class BuildNormalForm extends Listener {

  @Override
  public void initialize() {
    if (!has(NormalForms.class)) {
      set(new NormalForms());
    }
  }

  @Override
  public void enterChain(ChainContext ctx) {
    ReferenceContext c = ctx.reference();
    get(NormalForms.class).put(c, stringify(c));
  }

  @Override
  public void exitMethod(MethodContext ctx) {
    StringBuilder builder = new StringBuilder();
    if (has(StaticMethods.class) && get(StaticMethods.class).contains(ctx)) {
      builder.append("static").append(" ");
    }
    builder.append(stringify(ctx));
    if (has(ReturnExpressions.class) && get(ReturnExpressions.class).get(ctx) != null) {
      builder.append(" return ").append(get(ReturnExpressions.class).get(ctx));
    }
    get(NormalForms.class).put(ctx, builder.toString());
  }
}
