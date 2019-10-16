package com.github.tomokinakamaru.protocool._keep.symbol;

import com.github.tomokinakamaru.protocool.error.Error;

public class UndefinedType extends Error {

  public UndefinedType(String name) {
    super("Undefined type: %s", name);
  }
}
