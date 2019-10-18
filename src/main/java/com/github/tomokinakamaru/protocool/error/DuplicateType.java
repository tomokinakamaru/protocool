package com.github.tomokinakamaru.protocool.error;

public class DuplicateType extends Error {

  public DuplicateType(String name) {
    super("Duplicate type: %s", name);
  }
}
