package com.github.tomokinakamaru.protocool.analyzer.scope;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ClassContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class ClassScopeAnalyzer extends TreeAnalyzer {

  private ClassContext classContext;

  @Override
  public void enterClass(ClassContext ctx) {
    classContext = ctx;
  }

  @Override
  public void exitClass(ClassContext ctx) {
    classContext = null;
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    context.classScope.put(ctx, classContext);
  }
}
