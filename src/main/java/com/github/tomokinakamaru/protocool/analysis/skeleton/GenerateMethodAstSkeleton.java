package com.github.tomokinakamaru.protocool.analysis.skeleton;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.data.Skeleton;
import com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.MethodNodeBuilder;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;

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
