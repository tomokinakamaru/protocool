package com.github.tomokinakamaru.protocool.analysis.symbol;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ParameterContext;

public class BuildParameterTable extends Analysis {

  @Override
  public void enterParameter(ParameterContext ctx) {
    ctx.ownerClazz.parameterTable.put(ctx.name().getText(), ctx);
  }
}
