package com.github.tomokinakamaru.protocool.analyzer.syntax;

import com.github.tomokinakamaru.protocool.exception.SyntaxError;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorListener extends BaseErrorListener {

  @Override
  public void syntaxError(Recognizer r, Object o, int l, int c, String m, RecognitionException e) {
    throw new SyntaxError(l, c, m);
  }
}
