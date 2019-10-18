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

  private FileContext fileContext;

  @Override
  public void init() {
    typeTables = set(new TypeTables());
    fileContext = get(FileContext.class);
  }

  @Override
  public void enterFile(FileContext ctx) {
    typeTables.put(ctx, new TypeTable());
  }

  @Override
  public void enterImport(ImportContext ctx) {
    typeTables.get(fileContext).set(ctx.qualifiedName().getText(), ctx);
  }

  @Override
  public void enterClass(ClassContext ctx) {
    typeTables.get(fileContext).set(ctx.name().getText(), ctx);
    typeTables.put(ctx, typeTables.get(fileContext).createChildScope());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    typeTables.get(findParent(ClassContext.class, ctx)).set(ctx.name().getText(), ctx);
  }
}
