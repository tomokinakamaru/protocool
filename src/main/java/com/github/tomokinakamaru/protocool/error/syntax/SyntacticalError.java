package com.github.tomokinakamaru.protocool.error.syntax;

import com.github.tomokinakamaru.protocool.error.Error;

public class SyntacticalError extends Error {

  public SyntacticalError(int line, int position, String message) {
    super("Syntactical error: %s (L%dC%d)", message, line, position);
  }
}
