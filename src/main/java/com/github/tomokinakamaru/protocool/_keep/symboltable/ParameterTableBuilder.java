package com.github.tomokinakamaru.protocool._keep.symboltable;

import com.github.tomokinakamaru.protocool._keep.TreeAnalyzer;
import com.github.tomokinakamaru.protocool._keep.data.symboltable.ParameterTable;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ParameterContext;

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
