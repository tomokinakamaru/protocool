package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.data.symboltable.ImportTable;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.Import_Context;

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
