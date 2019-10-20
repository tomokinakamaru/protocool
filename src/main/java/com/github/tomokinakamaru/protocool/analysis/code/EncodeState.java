package com.github.tomokinakamaru.protocool.analysis.code;

import static com.github.tomokinakamaru.protocool.data.automaton.State.INITIAL_NUMBER;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.tomokinakamaru.protocool.analysis.abst.code.ApiClassBuilder;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.PackageContext;
import com.github.tomokinakamaru.protocool.data.ApiClasses;

public class EncodeState extends ApiClassBuilder {

  private CompilationUnit unit;

  private ClassOrInterfaceDeclaration decl;

  private static final String STATE_NAME_FORMAT = "State%d";

  @Override
  public void initialize() {
    set(new ApiClasses());
  }

  @Override
  protected void prepare() {
    unit = new CompilationUnit();
    decl = new ClassOrInterfaceDeclaration();
    unit.addType(decl);
    get(ApiClasses.class).put(state, unit);
  }

  @Override
  protected void build() {
    setPackage();
    setModifiers();
    setName();
  }

  private void setPackage() {
    unit.setPackageDeclaration(get(PackageContext.class).qualifiedName().getText());
  }

  private void setModifiers() {
    decl.setModifier(Modifier.Keyword.PUBLIC, true);
    if (state.number != INITIAL_NUMBER) {
      decl.setModifier(Modifier.Keyword.FINAL, true);
    }
  }

  private void setName() {
    if (state.number == INITIAL_NUMBER) {
      decl.setName(context.head().name().getText());
    } else {
      decl.setName(String.format(STATE_NAME_FORMAT, state.number));
    }
  }
}
