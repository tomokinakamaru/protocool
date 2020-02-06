package com.github.tomokinakamaru.protocool.analysis;

import static com.github.tomokinakamaru.utility.antlr4.Stringifier.stringify;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.context.NormalForms;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;

public class BuildNormalForms extends FileContextAnalysis {

  @Override
  public void initialize() {
    set(new NormalForms());
  }

  @Override
  public void enterChain(ChainContext ctx) {
    ReferenceContext c = ctx.reference();
    get(NormalForms.class).put(c, stringify(c));
  }

  @Override
  public void enterMethod(MethodContext ctx) {
    get(NormalForms.class).put(ctx, stringify(ctx));
  }
}
