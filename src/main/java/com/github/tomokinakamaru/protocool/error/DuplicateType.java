package com.github.tomokinakamaru.protocool.error;

public class DuplicateType extends RuntimeException {

  public DuplicateType(String name) {
    super("Duplicate type: " + name);
  }
}
