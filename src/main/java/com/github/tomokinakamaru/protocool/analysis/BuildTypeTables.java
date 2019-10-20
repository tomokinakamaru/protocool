package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;

public class BuildTypeTables extends Listener {

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
