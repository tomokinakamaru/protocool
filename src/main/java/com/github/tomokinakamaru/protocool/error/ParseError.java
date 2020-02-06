package com.github.tomokinakamaru.protocool.error;

public class ParseError extends RuntimeException {

  public ParseError(int line, int position, String message) {
    super(String.format("Parse error: %s (L%dC%d)", message, line, position));
  }
}
