package com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder;

import java.nio.file.Path;

abstract class FileBuilder extends CodeBuilder {

  public abstract void build();

  private Path path;

  public final Path getPath() {
    return path;
  }

  public final String getContent() {
    return super.toString();
  }

  final void setPath(Path path) {
    this.path = path;
  }
}
