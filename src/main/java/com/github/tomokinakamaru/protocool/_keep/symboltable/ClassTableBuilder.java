package com.github.tomokinakamaru.protocool._keep.symboltable;

import com.github.tomokinakamaru.protocool._keep.TreeAnalyzer;
import com.github.tomokinakamaru.protocool._keep.data.symboltable.ClassTable;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;

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
