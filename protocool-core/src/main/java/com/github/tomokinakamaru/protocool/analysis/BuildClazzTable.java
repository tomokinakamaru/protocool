package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;

public class BuildClazzTable extends Analysis {

  @Override
  public void enterClazz(ClazzContext ctx) {
    ctx.ownerSpecification.clazzTable.put(ctx.head().name().getText(), ctx);
  }
}
