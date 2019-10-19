package com.github.tomokinakamaru.protocool.analysis.tree;

import static com.github.tomokinakamaru.protocool.analysis.Utility.findClassContext;

import com.github.tomokinakamaru.protocool.analysis.abst.tree.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;

public class BuildTypeTable extends Listener {

  private TypeTables typeTables;

  private TypeTable rootTable;

  @Override
  public void initialize() {
    typeTables = set(new TypeTables());
    rootTable = new TypeTable();
    typeTables.put(get(FileContext.class), rootTable);
  }

  @Override
  public void enterImport(ImportContext ctx) {
    rootTable.set(ctx.qualifiedName().getText(), ctx);
  }

  @Override
  public void enterClass(ClassContext ctx) {
    rootTable.set(ctx.head().name().getText(), ctx);
    typeTables.put(ctx, rootTable.createChildScope());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    typeTables.get(findClassContext(ctx)).set(ctx.name().getText(), ctx);
  }
}
