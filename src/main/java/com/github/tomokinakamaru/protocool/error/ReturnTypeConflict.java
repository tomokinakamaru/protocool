package com.github.tomokinakamaru.protocool.error;

public class ReturnTypeConflict extends RuntimeException {

  public ReturnTypeConflict(String name) {
    super("Return type conflict:" + name);
  }
}
