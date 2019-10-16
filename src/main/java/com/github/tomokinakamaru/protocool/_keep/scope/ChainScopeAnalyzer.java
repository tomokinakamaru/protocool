package com.github.tomokinakamaru.protocool._keep.scope;

import com.github.tomokinakamaru.protocool._keep.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ChainContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class ChainScopeAnalyzer extends TreeAnalyzer {

  private ChainContext chainContext;

  @Override
  public void enterChain(ChainContext ctx) {
    chainContext = ctx;
  }

  @Override
  public void exitChain(ChainContext ctx) {
    chainContext = null;
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    context.chainScope.put(ctx, chainContext);
  }
}
