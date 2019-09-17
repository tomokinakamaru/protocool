package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ReferenceContext;

public class AssignOwnerClazz extends Analysis {

  private ClazzContext context;

  @Override
  public void enterClazz(ClazzContext ctx) {
    context = ctx;
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    ctx.ownerClazz = context;
  }

  @Override
  public void enterChain(ChainContext ctx) {
    ctx.ownerClazz = context;
  }

  @Override
  public void enterMethod(MethodContext ctx) {
    ctx.ownerClazz = context;
  }

  @Override
  public void enterReference(ReferenceContext ctx) {
    ctx.ownerClazz = context;
  }
}
