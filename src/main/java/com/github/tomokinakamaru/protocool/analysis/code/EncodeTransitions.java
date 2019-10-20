package com.github.tomokinakamaru.protocool.analysis.code;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.tomokinakamaru.protocool.analysis.abst.code.ApiMethodBuilder;
import com.github.tomokinakamaru.protocool.data.code.ApiClasses;
import com.github.tomokinakamaru.protocool.data.code.ClassTypes;

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
    if (transition.destination.context != null) {
      decl.setType(get(ClassTypes.class).get(transition.destination.context));
    } else {
      decl.setType(get(ApiClasses.class).get(transition.destination).getName().asString());
    }
  }
}
