package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.symboltable.ParameterTable;

public class ParameterTableBuilder extends TreeAnalyzer {

  @Override
  public void enterClass(ClassContext ctx) {
    context.parameterTables.put(ctx, new ParameterTable());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    context.parameterTables.get(context.classScope.get(ctx)).put(ctx.name().getText(), ctx);
  }
}
