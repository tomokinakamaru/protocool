package com.github.tomokinakamaru.protocool.skeleton;

import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;

public abstract class CommonSkeletonBuilder extends FileBuilder {

  protected abstract String getName();

  private final SpecificationContext context;

  protected CommonSkeletonBuilder(SpecificationContext context) {
    this.context = context;
  }

  @Override
  public void build() {
    String fileName = getName() + ".java";
    setPath(context.packagePath.resolve(fileName));

    String pkg = context.packageName;
    if (!pkg.isEmpty()) {
      append("package", pkg, ";");
    }
  }
}
