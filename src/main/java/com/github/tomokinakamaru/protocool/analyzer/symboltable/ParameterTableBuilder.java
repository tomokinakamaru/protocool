package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.data.symboltable.ParameterTable;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.Class_Context;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.ParameterContext;

public class ParameterTableBuilder extends TreeAnalyzer {

  @Override
  public void enterClass_(Class_Context ctx) {
    context.parameterTables.put(ctx, new ParameterTable());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    context.parameterTables.get(context.classContexts.get(ctx)).put(ctx.name().getText(), ctx);
  }
}
