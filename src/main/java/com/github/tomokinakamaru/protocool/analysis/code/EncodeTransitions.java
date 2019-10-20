package com.github.tomokinakamaru.protocool.analysis.code;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.tomokinakamaru.protocool.analysis.abst.code.ApiMethodBuilder;

public class EncodeTransitions extends ApiMethodBuilder {

  private MethodDeclaration decl;

  @Override
  protected void prepare() {
    decl = new MethodDeclaration();
    classDecl.addMember(decl);
  }

  @Override
  protected void build() {
    setName();
    setReturnType();
  }

  private void setName() {
    decl.setName(transition.symbol.asMethodContext().name().getText());
  }

  private void setReturnType() {
    decl.setType("X");
  }
}
