package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.data.ClassNodeNameTable;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ChainContext;
import java.util.ArrayList;

public class BuildClassNodeNameTable extends Analysis {

  @Override
  public void enterChain(ChainContext ctx) {
    ClassNodeNameTable table = ctx.ownerClazz.ownerSpecification.classNodeNameTable;
    table.putIfAbsent(ctx.nodeBaseName, new ArrayList<>());
    if (!table.get(ctx.nodeBaseName).contains(ctx.reference().normalizedText)) {
      table.get(ctx.nodeBaseName).add(ctx.reference().normalizedText);
    }
  }
}
