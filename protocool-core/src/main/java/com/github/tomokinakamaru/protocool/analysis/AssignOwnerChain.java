package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ReferenceContext;

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
