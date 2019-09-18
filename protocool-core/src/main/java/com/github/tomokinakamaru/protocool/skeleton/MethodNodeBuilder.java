package com.github.tomokinakamaru.protocool.skeleton;

import static com.github.tomokinakamaru.protocool.skeleton.Utility.buildParameters;
import static com.github.tomokinakamaru.protocool.skeleton.Utility.getText;

import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.MethodContext;

public class MethodNodeBuilder extends FileBuilder {

  private final MethodContext context;

  public MethodNodeBuilder(MethodContext context) {
    this.context = context;
  }

  @Override
  public void build() {
    buildPath();
    buildHeader();
    buildClassHead();
    append("{");
    buildClassBody();
    append("}");
  }

  private void buildPath() {
    String fileName = context.nodeName + ".java";
    setPath(context.ownerClazz.ownerSpecification.packagePath.resolve(fileName));
  }

  private void buildHeader() {
    String pkg = context.ownerClazz.ownerSpecification.packageName;
    if (!pkg.isEmpty()) {
      append("package", pkg, ";");
    }
  }

  private void buildClassHead() {
    append("public", "final", "class", context.nodeName);
    append(buildParameters(context.parameters, true));
    append("extends", "Method$");
  }

  private void buildClassBody() {
    context.argument().forEach(this::buildField);
  }

  private void buildField(ArgumentContext ctx) {
    append("public");
    append(getText(ctx.reference()));
    if (ctx.ELLIPSIS() != null) {
      append("[]");
    }
    append(ctx.name().getText());
    append(";");
  }
}
