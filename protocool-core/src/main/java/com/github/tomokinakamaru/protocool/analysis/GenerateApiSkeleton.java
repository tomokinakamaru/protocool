package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.StateAnalysis;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Skeleton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ClazzContext;
import com.github.tomokinakamaru.protocool.skeleton.ApiSkeletonBuilder;

public class GenerateApiSkeleton extends StateAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton, State state) {
    if (state.context == null || state.context.clazzDestination != null) {
      ApiSkeletonBuilder builder = new ApiSkeletonBuilder(ctx, automaton, state);
      builder.build();
      Skeleton skeleton = new Skeleton();
      skeleton.path = builder.getPath();
      skeleton.content = builder.getContent();
      ctx.ownerSpecification.skeletons.add(skeleton);
    }
  }
}
