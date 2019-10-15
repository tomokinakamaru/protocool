package com.github.tomokinakamaru.protocool.analyzer.symboltable;

import com.github.tomokinakamaru.protocool.analyzer.TreeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.data.symboltable.ClassTable;

public class ClassTableBuilder extends TreeAnalyzer {

  @Override
  public void enterFile(FileContext ctx) {
    context.classTables.put(ctx, new ClassTable());
  }

  @Override
  public void enterClass(ClassContext ctx) {
    context.classTables.get(context.tree).put(ctx.name().getText(), ctx);
  }
}
