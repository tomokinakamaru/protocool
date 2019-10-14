package com.github.tomokinakamaru.protocool.analysis.skeleton;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.ClassNodeBuilder;
import com.github.tomokinakamaru.protocool.data.Skeleton;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;
import java.util.Set;

public class GenerateClassAstSkeleton extends Analysis {

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    for (Set<ChainContext> contexts : ctx.classNodeTable.values()) {
      ClassNodeBuilder builder = new ClassNodeBuilder(contexts);
      builder.build();
      Skeleton skeleton = new Skeleton();
      skeleton.path = builder.getPath();
      skeleton.content = builder.getContent();
      ctx.skeletons.add(skeleton);
    }
  }
}
