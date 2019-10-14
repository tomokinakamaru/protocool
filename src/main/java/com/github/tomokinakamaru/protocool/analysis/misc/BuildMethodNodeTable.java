package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.data.MethodNodeTable;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;

public class BuildMethodNodeTable extends Analysis {

  @Override
  public void enterMethod(MethodContext ctx) {
    MethodNodeTable table = ctx.ownerClazz.ownerSpecification.methodNodeTable;
    table.putIfAbsent(ctx.nodeName, ctx);
  }
}
