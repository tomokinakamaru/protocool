package com.github.tomokinakamaru.protocool.error.reference;

import com.github.tomokinakamaru.protocool.error.Error;

public class DuplicateType extends Error {

  public DuplicateType(String name) {
    super("Duplicate type: %s", name);
  }
}
