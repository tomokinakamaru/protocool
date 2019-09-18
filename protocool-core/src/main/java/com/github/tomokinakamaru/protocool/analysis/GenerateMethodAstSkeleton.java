package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.data.Skeleton;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.skeleton.MethodNodeBuilder;

public class GenerateMethodAstSkeleton extends Analysis {

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    for (MethodContext context : ctx.methodNodeTable.values()) {
      MethodNodeBuilder builder = new MethodNodeBuilder(context);
      builder.build();
      Skeleton skeleton = new Skeleton();
      skeleton.path = builder.getPath();
      skeleton.content = builder.getContent();
      ctx.skeletons.add(skeleton);
    }
  }
}
