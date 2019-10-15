package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.data.symboltable.ClassTable;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.Class_Context;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.FileContext;

public class ClassTableBuilder extends TreeAnalyzer {

  @Override
  public void enterFile(FileContext ctx) {
    context.classTables.put(ctx, new ClassTable());
  }

  @Override
  public void enterClass_(Class_Context ctx) {
    context.classTables.get(context.fileContext).put(ctx.name().getText(), ctx);
  }
}
