package com.github.tomokinakamaru.protocool.analyzer.reference;

import static com.github.tomokinakamaru.protocool.analyzer.Utility.findParent;

import com.github.tomokinakamaru.protocool.analyzer.Listener;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.reference.Table;

public class Resolver extends Listener {

  @Override
  public void enterReference(ReferenceContext ctx) {
    Table table = context.tables.get(findParent(ClassContext.class, ctx));
    context.entities.put(ctx, table.get(ctx.qualifiedName().getText()));
  }
}
