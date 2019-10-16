package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.data.symboltable.ClassTable;

public class ClassTableBuilder extends TreeAnalyzer {

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    context.classTables.put(ctx, new ClassTable());
  }

  @Override
  public void enterClass(ClassContext ctx) {
    context.classTables.get(context.tree).put(ctx.name().getText(), ctx);
  }
}
