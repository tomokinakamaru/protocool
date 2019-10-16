package com.github.tomokinakamaru.protocool.analyzer.reference;

import static com.github.tomokinakamaru.protocool.analyzer.Utility.findParent;

import com.github.tomokinakamaru.protocool.analyzer.Listener;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.data.reference.Table;

public class TableBuilder extends Listener {

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    context.tables.put(ctx, new Table());
  }

  @Override
  public void enterImport(ImportContext ctx) {
    context.tables.get(context.specificationContext).set(ctx.qualifiedName().getText(), ctx);
  }

  @Override
  public void enterClass(ClassContext ctx) {
    Table table = context.tables.get(context.specificationContext);
    table.set(ctx.name().getText(), ctx);
    context.tables.put(ctx, table.newChild());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    context.tables.get(findParent(ClassContext.class, ctx)).set(ctx.name().getText(), ctx);
  }
}
