package com.github.tomokinakamaru.protocool.analyzer.scope;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.Class_Context;
import org.antlr.v4.runtime.ParserRuleContext;

public class ClassScopeAnalyzer extends TreeAnalyzer {

  private Class_Context classContext;

  @Override
  public void enterClass_(Class_Context ctx) {
    classContext = ctx;
  }

  @Override
  public void exitClass_(Class_Context ctx) {
    classContext = null;
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    context.classContexts.put(ctx, classContext);
  }
}
