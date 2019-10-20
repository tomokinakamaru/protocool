package com.github.tomokinakamaru.protocool.analysis.code;

import static com.github.tomokinakamaru.protocool.data.automaton.State.INITIAL_NUMBER;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.tomokinakamaru.protocool.analysis.abst.code.StateClassBuilder;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.PackageContext;
import com.github.tomokinakamaru.protocool.data.StateClasses;

public class EncodeState extends StateClassBuilder {

  private CompilationUnit unit;

  private ClassOrInterfaceDeclaration decl;

  private static final String STATE_NAME_FORMAT = "State%d";

  @Override
  public void initialize() {
    set(new StateClasses());
  }

  @Override
  protected void prepare() {
    unit = new CompilationUnit();
    decl = new ClassOrInterfaceDeclaration();
    unit.addType(decl);
    get(StateClasses.class).put(state, unit);
  }

  @Override
  protected void build() {
    buildPackage();
    buildClassName();
  }

  private void buildPackage() {
    unit.setPackageDeclaration(get(PackageContext.class).qualifiedName().getText());
  }

  private void buildClassName() {
    if (state.number == INITIAL_NUMBER) {
      decl.setName(ctx.head().name().getText());
    } else {
      decl.setName(String.format(STATE_NAME_FORMAT, state.number));
    }
  }
}
