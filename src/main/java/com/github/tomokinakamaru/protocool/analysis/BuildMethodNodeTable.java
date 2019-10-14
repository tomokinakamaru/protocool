package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.Analysis;
import com.github.tomokinakamaru.protocool.data.MethodNodeTable;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;

public class BuildMethodNodeTable extends Analysis {

  @Override
  public void enterMethod(MethodContext ctx) {
    MethodNodeTable table = ctx.ownerClazz.ownerSpecification.methodNodeTable;
    table.putIfAbsent(ctx.nodeName, ctx);
  }
}
