package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.protocool.data.ApiClasses;
import com.github.tomokinakamaru.protocool.data.skeleton.Skeleton;
import com.github.tomokinakamaru.protocool.data.skeleton.Skeletons;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuildSkeletons extends AbstractAnalyzer {

  @Override
  public void initialize() {
    set(new Skeletons());
  }

  @Override
  public void analyze() {
    get(ApiClasses.class).values().forEach(this::buildSkeleton);
  }

  private void buildSkeleton(CompilationUnit compilationUnit) {
    Skeleton skeleton = new Skeleton();
    skeleton.path = getPackagePath(compilationUnit).resolve(getFileName(compilationUnit));
    skeleton.compilationUnit = compilationUnit;
    get(Skeletons.class).add(skeleton);
  }

  private static Path getPackagePath(CompilationUnit compilationUnit) {
    return compilationUnit
        .getPackageDeclaration()
        .map(p -> p.getName().asString())
        .map(s -> s.split("\\."))
        .map(BuildSkeletons::getPackagePath)
        .orElse(Paths.get(""));
  }

  private static Path getPackagePath(String[] ss) {
    Path path = Paths.get("");
    for (String s : ss) {
      path = path.resolve(s);
    }
    return path;
  }

  private static String getFileName(CompilationUnit compilationUnit) {
    return compilationUnit.getType(0).getName().asString() + ".java";
  }
}
