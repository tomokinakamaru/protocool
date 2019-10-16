package com.github.tomokinakamaru.protocool.error;

public abstract class Error extends RuntimeException {

  protected Error(String format, Object... objects) {
    super(String.format(format, objects));
  }
}
