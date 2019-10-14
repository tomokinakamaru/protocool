package com.github.tomokinakamaru.protocool.analysis.syntax;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;

public class AssignOwnerChain extends Analysis {

  private ChainContext context;

  @Override
  public void enterChain(ChainContext ctx) {
    context = ctx;
  }

  @Override
  public void enterReference(ReferenceContext ctx) {
    ctx.ownerChain = context;
  }
}
