package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.PackageContext;
import com.github.tomokinakamaru.protocool.context.ApiClasses;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;

public class SetPackages extends FileContextAnalysis {

  @Override
  public void enterPackage(PackageContext ctx) {
    for (CompilationUnit unit : get(ApiClasses.class).values()) {
      unit.setPackageDeclaration(ctx.qualifiedName().getText());
    }
    for (CompilationUnit unit : get(AstBases.class).values()) {
      unit.setPackageDeclaration(ctx.qualifiedName().getText());
    }
    for (CompilationUnit unit : get(ClassNodes.class).getCompilationUnits()) {
      unit.setPackageDeclaration(ctx.qualifiedName().getText());
    }
    for (CompilationUnit unit : get(MethodNodes.class).getCompilationUnits()) {
      unit.setPackageDeclaration(ctx.qualifiedName().getText());
    }

    CreateVisitor.VISITOR.setPackageDeclaration(ctx.qualifiedName().getText());
  }
}
