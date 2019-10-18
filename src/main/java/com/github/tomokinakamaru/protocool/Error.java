package com.github.tomokinakamaru.protocool;

public final class Error {

  private Error() {}

  public static class ParseError extends RuntimeException {
    public ParseError(int line, int position, String message) {
      super(String.format("Parse error: %s (L%dC%d)", message, line, position));
    }
  }

  public static class DuplicateType extends RuntimeException {
    public DuplicateType(String name) {
      super("Duplicate type: " + name);
    }
  }

  public static class UndefinedType extends RuntimeException {
    public UndefinedType(String name) {
      super("Undefined type: " + name);
    }
  }
}
