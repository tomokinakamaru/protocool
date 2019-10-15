package com.github.tomokinakamaru.protocool.exception;

import java.util.stream.Stream;

public class DuplicateType extends RuntimeException {

  public DuplicateType(Stream name) {
    super("Duplicate type: " + name);
  }
}
