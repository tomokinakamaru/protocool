package com.github.tomokinakamaru.protocool.exception;

public class SyntaxError extends RuntimeException {

  public SyntaxError(int line, int position, String message) {
    super(String.format("%s (L%dC%d)", message, line, position));
  }
}
