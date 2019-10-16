package com.github.tomokinakamaru.protocool._keep.symboltable;

import com.github.tomokinakamaru.protocool._keep.TreeAnalyzer;
import com.github.tomokinakamaru.protocool._keep.data.symboltable.ImportTable;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;

public class ImportTableBuilder extends TreeAnalyzer {

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    context.importTables.put(ctx, new ImportTable());
  }

  @Override
  public void enterImport(ImportContext ctx) {
    context.importTables.get(context.tree).put(ctx.qualifiedName().getText(), ctx);
  }
}
