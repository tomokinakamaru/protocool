package com.github.tomokinakamaru.protocool._keep.scope;

import com.github.tomokinakamaru.protocool._keep.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
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
