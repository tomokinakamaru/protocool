package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.Analysis;
import com.github.tomokinakamaru.protocool.data.ForeignType;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;

public class BuildForeignTypeTable extends Analysis {

  @Override
  public void enterReference(ReferenceContext ctx) {
    String name = ctx.qualifiedName().getText();
    if (ctx.ownerClazz.parameterTable.containsKey(name)) {
      return;
    }
    if (ctx.ownerClazz.ownerSpecification.clazzTable.containsKey(name)) {
      return;
    }
    ctx.ownerClazz.ownerSpecification.foreignTypeTable.putIfAbsent(name, new ForeignType());
  }
}
