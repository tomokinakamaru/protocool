package com.github.tomokinakamaru.protocool.error;

public class ParseError extends Error {

  public ParseError(int line, int position, String message) {
    super("Parse error: %s (L%dC%d)", message, line, position);
  }
}
