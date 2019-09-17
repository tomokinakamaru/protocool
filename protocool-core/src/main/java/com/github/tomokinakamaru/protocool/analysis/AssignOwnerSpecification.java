package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.PkgContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.SpecificationContext;

public class AssignOwnerSpecification extends Analysis {

  private SpecificationContext context;

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    context = ctx;
  }

  @Override
  public void enterPkg(PkgContext ctx) {
    ctx.ownerSpecification = context;
  }

  @Override
  public void enterClazz(ClazzContext ctx) {
    ctx.ownerSpecification = context;
  }
}
