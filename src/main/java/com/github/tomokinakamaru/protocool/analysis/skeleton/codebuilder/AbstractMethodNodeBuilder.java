package com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder;

import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;

public class AbstractMethodNodeBuilder extends CommonSkeletonBuilder {

  public AbstractMethodNodeBuilder(SpecificationContext context) {
    super(context);
  }

  @Override
  protected String getName() {
    return "Method$";
  }

  @Override
  public void build() {
    super.build();
    append("public", "abstract", "class", getName(), "{}");
  }
}
