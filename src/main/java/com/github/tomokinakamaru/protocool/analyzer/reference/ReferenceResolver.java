package com.github.tomokinakamaru.protocool.analyzer.reference;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.data.symboltable.ParameterTable;
import com.github.tomokinakamaru.protocool.exception.UndefinedType;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.ReferenceContext;

public class ReferenceResolver extends TreeAnalyzer {

  @Override
  public void enterReference(ReferenceContext ctx) {
    String name = ctx.qualifiedName().getText();
    if (context.parameterTables.get(context.classContexts.get(ctx)).containsKey(name)) {
      ParameterTable table = context.parameterTables.get(context.classContexts.get(ctx));
      context.references.put(ctx, table.get(name));
    } else if (context.classTables.get(context.fileContext).containsKey(name)) {
      context.references.put(ctx, context.classTables.get(context.fileContext).get(name));
    } else if (context.importTables.get(context.fileContext).containsKey(name)) {
      context.references.put(ctx, context.importTables.get(context.fileContext).get(name));
    } else {
      throw new UndefinedType(name);
    }
  }
}
