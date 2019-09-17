package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ParameterContext;

public class BuildParameterTable extends Analysis {

  @Override
  public void enterParameter(ParameterContext ctx) {
    ctx.ownerClazz.parameterTable.put(ctx.name().getText(), ctx);
  }
}
