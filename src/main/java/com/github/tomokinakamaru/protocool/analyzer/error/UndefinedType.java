package com.github.tomokinakamaru.protocool.analyzer.error;

public class UndefinedType extends Error {

  public UndefinedType(String name) {
    super("Undefined type: %s", name);
  }
}
