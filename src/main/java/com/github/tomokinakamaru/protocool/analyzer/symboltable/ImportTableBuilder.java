package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.Import_Context;
import com.github.tomokinakamaru.protocool.data.symboltable.ImportTable;

public class ImportTableBuilder extends TreeAnalyzer {

  @Override
  public void enterFile(FileContext ctx) {
    context.importTables.put(ctx, new ImportTable());
  }

  @Override
  public void enterImport_(Import_Context ctx) {
    context.importTables.get(context.fileContext).put(ctx.qualifiedName().getText(), ctx);
  }
}
