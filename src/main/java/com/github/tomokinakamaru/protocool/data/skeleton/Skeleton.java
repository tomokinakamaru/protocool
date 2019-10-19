package com.github.tomokinakamaru.protocool.data.skeleton;

import com.github.javaparser.ast.CompilationUnit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Skeleton {

  public Path path;

  public CompilationUnit compilationUnit;

  public void save(Path directory) throws IOException {
    Files.write(directory.resolve(path), compilationUnit.toString().getBytes());
  }
}
