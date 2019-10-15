package com.github.tomokinakamaru.protocool.analyzer.scope;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.ChainContext;
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
    context.chainContexts.put(ctx, chainContext);
  }
}
