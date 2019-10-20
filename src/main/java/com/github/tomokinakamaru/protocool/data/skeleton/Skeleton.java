package com.github.tomokinakamaru.protocool.data.skeleton;

import com.github.javaparser.ast.CompilationUnit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Skeleton {

  public CompilationUnit compilationUnit;

  public void save(Path directory) throws IOException {
    Path path = directory.resolve(getPath());
    Files.createDirectories(path.getParent());
    Files.write(path, compilationUnit.toString().getBytes());
  }

  public Path getPath() {
    return getPackagePath().resolve(compilationUnit.getType(0).getName().asString() + ".java");
  }

  private Path getPackagePath() {
    return compilationUnit
        .getPackageDeclaration()
        .map(p -> p.getName().asString())
        .map(s -> s.split("\\."))
        .map(Skeleton::getPackagePath)
        .orElse(Paths.get(""));
  }

  private static Path getPackagePath(String[] ss) {
    Path path = Paths.get("");
    for (String s : ss) {
      path = path.resolve(s);
    }
    return path;
  }
}
