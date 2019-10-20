package com.github.tomokinakamaru.protocool.analysis.tree;

import com.github.tomokinakamaru.protocool.analysis.abst.tree.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.PackageContext;

public class StorePackage extends Listener {

  @Override
  public void enterPackage(PackageContext ctx) {
    set(ctx);
  }
}
