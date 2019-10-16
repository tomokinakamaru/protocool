package com.github.tomokinakamaru.protocool.error.syntax;

import com.github.tomokinakamaru.protocool.error.Error;

public class LexicalError extends Error {

  public LexicalError(int line, int position, String message) {
    super("Lexical error: %s (L%dC%d)", message, line, position);
  }
}
