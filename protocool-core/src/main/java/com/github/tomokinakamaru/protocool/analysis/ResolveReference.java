package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.SpecificationContext;

public class ResolveReference extends Analysis {

  @Override
  public void enterReference(ReferenceContext ctx) {
    String name = ctx.qualifiedName().getText();
    ClazzContext clazz = ctx.ownerClazz;
    SpecificationContext spec = clazz.ownerSpecification;

    if (clazz.parameterTable.containsKey(name)) {
      ctx.parameterDestination = clazz.parameterTable.get(name);
    } else if (spec.clazzTable.containsKey(name)) {
      ctx.clazzDestination = spec.clazzTable.get(name);
    } else if (spec.foreignTypeTable.containsKey(name)) {
      ctx.foreignTypeDestination = spec.foreignTypeTable.get(name);
    } else {
      throw new RuntimeException();
    }
  }
}
