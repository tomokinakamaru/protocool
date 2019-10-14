package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.data.MethodNodeNameTable;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import java.util.ArrayList;

public class BuildMethodNodeNameTable extends Analysis {

  @Override
  public void enterMethod(MethodContext ctx) {
    MethodNodeNameTable table = ctx.ownerClazz.ownerSpecification.methodNodeNameTable;
    table.putIfAbsent(ctx.nodeBaseName, new ArrayList<>());
    if (!table.get(ctx.nodeBaseName).contains(ctx.normalizedText)) {
      table.get(ctx.nodeBaseName).add(ctx.normalizedText);
    }
  }
}
