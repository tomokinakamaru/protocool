package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.data.Skeleton;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.skeleton.AbstractMethodNodeBuilder;
import com.github.tomokinakamaru.protocool.skeleton.CommonSkeletonBuilder;

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