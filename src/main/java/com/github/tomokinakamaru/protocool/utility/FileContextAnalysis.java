package com.github.tomokinakamaru.protocool.utility;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.antlr.Listener;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class FileContextAnalysis extends Listener {

  @Override
  protected final ParserRuleContext getContext() {
    return get(FileContext.class);
  }
}
