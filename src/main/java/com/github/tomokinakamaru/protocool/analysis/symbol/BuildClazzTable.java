package com.github.tomokinakamaru.protocool.analysis.symbol;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;

public class BuildClazzTable extends Analysis {

  @Override
  public void enterClazz(ClazzContext ctx) {
    ctx.ownerSpecification.clazzTable.put(ctx.head().name().getText(), ctx);
  }
}
