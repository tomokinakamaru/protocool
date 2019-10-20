package com.github.tomokinakamaru.protocool.analysis.code;

import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.PackageContext;

public class FindPackage extends Listener {

  @Override
  public void enterPackage(PackageContext ctx) {
    set(ctx);
  }
}
