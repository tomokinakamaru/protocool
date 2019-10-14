package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.data.ClassNodeTable;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import java.util.LinkedHashSet;

public class BuildClassNodeTable extends Analysis {

  @Override
  public void enterChain(ChainContext ctx) {
    ClassNodeTable table = ctx.ownerClazz.ownerSpecification.classNodeTable;
    table.putIfAbsent(ctx.nodeName, new LinkedHashSet<>());
    table.get(ctx.nodeName).add(ctx);
  }
}