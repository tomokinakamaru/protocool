package com.github.tomokinakamaru.protocool.analyzer.error;

abstract class Error extends RuntimeException {

  Error(String format, Object... objects) {
    super(String.format(format, objects));
  }
}
