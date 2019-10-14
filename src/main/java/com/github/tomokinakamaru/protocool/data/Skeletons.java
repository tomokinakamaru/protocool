package com.github.tomokinakamaru.protocool.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashSet;

public class Skeletons extends LinkedHashSet<Skeleton> {

  public void save(Path rootDirectory) throws IOException {
    for (Skeleton skeleton : this) {
      skeleton.save(rootDirectory);
    }
  }
}
