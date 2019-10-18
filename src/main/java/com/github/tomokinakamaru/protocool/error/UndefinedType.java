package com.github.tomokinakamaru.protocool.error;

public class UndefinedType extends RuntimeException {

  public UndefinedType(String name) {
    super("Undefined type: " + name);
  }
}
