package com.github.tomokinakamaru.protocool.analysis.skeleton;

import com.github.tomokinakamaru.protocool.analysis.StateAnalysis;
import com.github.tomokinakamaru.protocool.analysis.data.Skeleton;
import com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder.ApiBuilder;
import com.github.tomokinakamaru.protocool.automaton.Automaton;
import com.github.tomokinakamaru.protocool.automaton.State;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ClazzContext;

public class GenerateApiSkeleton extends StateAnalysis {

  @Override
  protected void process(ClazzContext ctx, Automaton automaton, State state) {
    if (state.context == null || state.context.clazzDestination != null) {
      ApiBuilder builder = new ApiBuilder(ctx, automaton, state);
      builder.build();
      Skeleton skeleton = new Skeleton();
      skeleton.path = builder.getPath();
      skeleton.content = builder.getContent();
      ctx.ownerSpecification.skeletons.add(skeleton);
    }
  }
}
