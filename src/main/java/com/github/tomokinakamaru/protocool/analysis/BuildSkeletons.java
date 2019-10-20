package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.PackageContext;
import com.github.tomokinakamaru.protocool.data.ApiClasses;
import com.github.tomokinakamaru.protocool.data.skeleton.Skeleton;
import com.github.tomokinakamaru.protocool.data.skeleton.Skeletons;
import java.util.Collection;
import java.util.stream.Stream;

public class BuildSkeletons extends AbstractAnalyzer {

  @Override
  public void initialize() {
    set(new Skeletons());
  }

  @Override
  public void analyze() {
    buildSkeletons(get(ApiClasses.class).values()).forEach(get(Skeletons.class)::add);
  }

  private Stream<Skeleton> buildSkeletons(Collection<ClassOrInterfaceDeclaration> decls) {
    return decls.stream().map(this::buildSkeleton);
  }

  private Skeleton buildSkeleton(ClassOrInterfaceDeclaration decl) {
    Skeleton skeleton = new Skeleton();
    skeleton.compilationUnit = buildCompilationUnit(decl);
    return skeleton;
  }

  private CompilationUnit buildCompilationUnit(ClassOrInterfaceDeclaration decl) {
    CompilationUnit unit = new CompilationUnit();
    unit.setPackageDeclaration(get(PackageContext.class).qualifiedName().getText());
    unit.addType(decl);
    return unit;
  }
}
