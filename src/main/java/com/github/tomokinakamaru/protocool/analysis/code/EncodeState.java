package com.github.tomokinakamaru.protocool.analysis.code;

import static com.github.tomokinakamaru.protocool.data.automaton.State.INITIAL_NUMBER;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.tomokinakamaru.protocool.analysis.abst.code.StateClassBuilder;
import com.github.tomokinakamaru.protocool.data.StateClasses;

public class EncodeState extends StateClassBuilder {

  private ClassOrInterfaceDeclaration clazz;

  @Override
  public void initialize() {
    set(new StateClasses());
  }

  @Override
  protected void build() {
    CompilationUnit unit = new CompilationUnit();
    clazz = unit.addClass(buildClassName());
    get(StateClasses.class).put(getState(), unit);
  }

  private String buildClassName() {
    if (getState().number == INITIAL_NUMBER) {
      return getClassContext().head().name().getText();
    } else {
      return "State" + getState().number;
    }
  }
}
