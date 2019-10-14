package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.data.ClassNodeNameTable;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;

public class BuildClassNodeName extends Analysis {

  @Override
  public void enterChain(ChainContext ctx) {
    ClassNodeNameTable table = ctx.ownerClazz.ownerSpecification.classNodeNameTable;
    ctx.nodeName = "Class$" + ctx.nodeBaseName;
    if (1 < table.get(ctx.nodeBaseName).size()) {
      int index = table.get(ctx.nodeBaseName).indexOf(ctx.reference().normalizedText);
      ctx.nodeName += "$" + (index + 1);
    }
  }
}
