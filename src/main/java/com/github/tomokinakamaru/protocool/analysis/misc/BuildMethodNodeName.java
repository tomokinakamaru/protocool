package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.data.MethodNodeNameTable;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;

public class BuildMethodNodeName extends Analysis {

  @Override
  public void enterMethod(MethodContext ctx) {
    MethodNodeNameTable table = ctx.ownerClazz.ownerSpecification.methodNodeNameTable;
    ctx.nodeName = "Method$" + ctx.nodeBaseName;
    if (1 < table.get(ctx.nodeBaseName).size()) {
      int index = table.get(ctx.nodeBaseName).indexOf(ctx.normalizedText);
      ctx.nodeName += "$" + (index + 1);
    }
  }
}
