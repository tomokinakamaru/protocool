package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.context.TypeTables;
import com.github.tomokinakamaru.protocool.data.TypeTable;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;

public class BuildTypeTables extends FileContextAnalysis {

  private TypeTable table;

  @Override
  public void initialize() {
    set(new TypeTables());
  }

  @Override
  public void enterFile(FileContext ctx) {
    table = new TypeTable();
  }

  @Override
  public void enterImport(ImportContext ctx) {
    table.set(ctx.qualifiedName().getText(), ctx);
  }

  @Override
  public void enterClass(ClassContext ctx) {
    table.set(ctx.head().name().getText(), ctx);
    table = table.createChildScope();
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    table.set(ctx.name().getText(), ctx);
  }

  @Override
  public void enterReference(ReferenceContext ctx) {
    get(TypeTables.class).put(ctx, table);
  }

  @Override
  public void exitClass(ClassContext ctx) {
    table = table.getParent();
  }
}
