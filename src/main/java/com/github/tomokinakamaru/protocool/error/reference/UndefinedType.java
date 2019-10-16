package com.github.tomokinakamaru.protocool.error.reference;

import com.github.tomokinakamaru.protocool.error.Error;

public class UndefinedType extends Error {

  public UndefinedType(String name) {
    super("Undefined type: %s", name);
  }
}
