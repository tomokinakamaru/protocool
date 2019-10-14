package com.github.tomokinakamaru.protocool.analysis.skeleton;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.data.Skeleton;
import com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.AbstractMethodNodeBuilder;
import com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.CommonSkeletonBuilder;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;

public class GenerateBaseFile extends Analysis {

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    ctx.skeletons.add(generateAbstractMethodNode(ctx));
  }

  private Skeleton generateAbstractMethodNode(SpecificationContext ctx) {
    return run(new AbstractMethodNodeBuilder(ctx));
  }

  private static Skeleton run(CommonSkeletonBuilder builder) {
    builder.build();
    Skeleton skeleton = new Skeleton();
    skeleton.path = builder.getPath();
    skeleton.content = builder.getContent();
    return skeleton;
  }
}
