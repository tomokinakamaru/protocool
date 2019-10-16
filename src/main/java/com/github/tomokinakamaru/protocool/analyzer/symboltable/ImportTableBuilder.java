package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.data.symboltable.ImportTable;

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
