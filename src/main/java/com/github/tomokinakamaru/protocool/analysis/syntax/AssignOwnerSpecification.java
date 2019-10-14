package com.github.tomokinakamaru.protocool.analysis.syntax;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ForeignTypeContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.PkgContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;

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
  public void enterForeignType(ForeignTypeContext ctx) {
    ctx.ownerSpecification = context;
  }

  @Override
  public void enterClazz(ClazzContext ctx) {
    ctx.ownerSpecification = context;
  }
}
