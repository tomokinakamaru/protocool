package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.PkgContext;
import java.nio.file.Paths;

public class AssignPackage extends Analysis {

  @Override
  public void enterPkg(PkgContext ctx) {
    String name = ctx.qualifiedName().getText();
    ctx.ownerSpecification.packageName = name;
    ctx.ownerSpecification.packagePath = Paths.get("", name.split("\\."));
  }
}
