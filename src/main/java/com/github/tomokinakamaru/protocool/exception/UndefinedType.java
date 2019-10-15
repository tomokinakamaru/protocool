package com.github.tomokinakamaru.protocool.exception;

public class UndefinedType extends RuntimeException {

  public UndefinedType(String name) {
    super("Undefined type: " + name);
  }
}
