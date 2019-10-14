package com.github.tomokinakamaru.protocool.analysis.symbol;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ForeignTypeContext;

public class BuildForeignTypeTable extends Analysis {

  @Override
  public void enterForeignType(ForeignTypeContext ctx) {
    ctx.ownerSpecification.foreignTypeTable.put(ctx.qualifiedName().getText(), ctx);
  }
}
