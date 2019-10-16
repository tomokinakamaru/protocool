package com.github.tomokinakamaru.protocool.analyzer.reference;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.symboltable.ParameterTable;
import com.github.tomokinakamaru.protocool.exception.UndefinedType;

public class ReferenceResolver extends TreeAnalyzer {

  @Override
  public void enterReference(ReferenceContext ctx) {
    String name = ctx.qualifiedName().getText();
    if (context.parameterTables.get(context.classScope.get(ctx)).containsKey(name)) {
      ParameterTable table = context.parameterTables.get(context.classScope.get(ctx));
      context.references.put(ctx, table.get(name));
    } else if (context.classTables.get(context.tree).containsKey(name)) {
      context.references.put(ctx, context.classTables.get(context.tree).get(name));
    } else if (context.importTables.get(context.tree).containsKey(name)) {
      context.references.put(ctx, context.importTables.get(context.tree).get(name));
    } else {
      throw new UndefinedType(name);
    }
  }
}
