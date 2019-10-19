package com.github.tomokinakamaru.protocool.data.skeleton;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

public class Skeletons extends HashSet<Skeleton> {

  public void save(Path directory) throws IOException {
    for (Skeleton s : this) {
      s.save(directory);
    }
  }
}
