package com.github.tomokinakamaru.protocool.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorListener extends BaseErrorListener {

  static final ErrorListener INSTANCE = new ErrorListener();

  private ErrorListener() {}

  @Override
  public void syntaxError(Recognizer r, Object o, int l, int c, String m, RecognitionException e) {
    throw new RuntimeException(String.format("%s (L%dC%d)", m, l, c));
  }
}
