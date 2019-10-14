package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.Analysis;
import com.github.tomokinakamaru.protocool.data.Parameters;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;
import java.util.Stack;

public class AssignParameters extends Analysis {

  private final Stack<Parameters> stack = new Stack<>();

  @Override
  public void enterChain(ChainContext ctx) {
    stack.push(ctx.parameters);
  }

  @Override
  public void exitChain(ChainContext ctx) {
    stack.pop();
  }

  @Override
  public void enterMethod(MethodContext ctx) {
    stack.push(ctx.parameters);
  }

  @Override
  public void exitMethod(MethodContext ctx) {
    stack.pop();
  }

  @Override
  public void enterReference(ReferenceContext ctx) {
    stack.add(ctx.parameters);

    if (ctx.parameterDestination != null) {
      for (Parameters ps : stack) {
        ps.add(ctx.parameterDestination);
      }
    }
  }

  @Override
  public void exitReference(ReferenceContext ctx) {
    stack.pop();
  }
}
