package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;

public class BuildClassNodeBaseName extends Analysis {

  @Override
  public void enterChain(ChainContext ctx) {
    String name = ctx.reference().qualifiedName().getText().replace(".", "_");
    if (ctx.reference().parameterDestination == null) {
      ctx.nodeBaseName = name;
    } else {
      ctx.nodeBaseName = ctx.ownerClazz.head().name().getText() + "$" + name;
    }
  }
}
