package com.github.tomokinakamaru.protocool.analyzer;

import static com.github.tomokinakamaru.antlr4.utility.NodeFinder.findParent;

import com.github.tomokinakamaru.protocool.analyzer.abst.Listener;
import com.github.tomokinakamaru.protocool.analyzer.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.antlr.SpecificationParser.FileContext;
import com.github.tomokinakamaru.protocool.analyzer.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analyzer.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;

public class TypeTableBuilder extends Listener {

  private TypeTables typeTables;

  private TypeTable rootTable;

  @Override
  public void init() {
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
    rootTable.set(ctx.name().getText(), ctx);
    typeTables.put(ctx, rootTable.createChildScope());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    typeTables.get(findParent(ClassContext.class, ctx)).set(ctx.name().getText(), ctx);
  }
}
