package com.github.tomokinakamaru.protocool.analysis.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Skeleton {

  public Path path;

  public String content;

  @SuppressWarnings("ResultOfMethodCallIgnored")
  public void save(Path rootDirectory) throws IOException {
    Path filePath = rootDirectory.resolve(path);
    filePath.getParent().toFile().mkdirs();
    Files.write(filePath, content.getBytes());
  }
}
