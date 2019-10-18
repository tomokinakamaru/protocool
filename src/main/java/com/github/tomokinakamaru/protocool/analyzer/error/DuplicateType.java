package com.github.tomokinakamaru.protocool.analyzer.error;

public class DuplicateType extends Error {

  public DuplicateType(String name) {
    super("Duplicate type: %s", name);
  }
}
