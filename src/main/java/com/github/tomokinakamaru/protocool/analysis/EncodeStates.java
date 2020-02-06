package com.github.tomokinakamaru.protocool.analysis;

import static com.github.tomokinakamaru.protocool.analysis.CreateBaseNodes.METHOD_NODE;
import static com.github.tomokinakamaru.protocool.data.State.INITIAL_NUMBER;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.context.ApiClasses;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.protocool.context.ReferenceTypes;
import com.github.tomokinakamaru.protocool.context.TypeParameters;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.utility.StateAnalysis;

public class EncodeStates extends StateAnalysis {

  private ClassContext context;

  private State state;

  private ClassOrInterfaceDeclaration decl;

  private static final String STATE_NAME_FORMAT = "State%d";

  @Override
  public void initialize() {
    set(new ApiClasses());
  }

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton, State state) {
    if (state.context != null) {
      return;
    }

    context = ctx;
    this.state = state;
    decl = new ClassOrInterfaceDeclaration();

    setModifiers();
    setName();
    setTypeParameters();
    setSuperClass();
    setInterfaces();
    setFields();

    CompilationUnit unit = new CompilationUnit();
    unit.addType(decl);
    get(ApiClasses.class).put(this.state, unit);
  }

  private void setModifiers() {
    decl.setModifier(Modifier.Keyword.PUBLIC, true);
  }

  private void setName() {
    if (state.number == INITIAL_NUMBER) {
      decl.setName(context.head().name().getText());
    } else {
      decl.setName(String.format(STATE_NAME_FORMAT, state.number));
    }
  }

  private void setTypeParameters() {
    for (ParameterContext c : state.parameters) {
      decl.addTypeParameter(get(TypeParameters.class).get(c));
    }
  }

  private void setSuperClass() {
    if (context.head().superClass() != null) {
      ReferenceContext c = context.head().superClass().reference();
      ClassOrInterfaceType t = get(ReferenceTypes.class).get(c).asClassOrInterfaceType();
      decl.setExtendedTypes(new NodeList<>(t));
    }
  }

  private void setInterfaces() {
    if (context.head().interfaces() != null) {
      NodeList<ClassOrInterfaceType> list = new NodeList<>();
      for (ReferenceContext c : context.head().interfaces().reference()) {
        list.add(get(ReferenceTypes.class).get(c).asClassOrInterfaceType());
      }
      decl.setImplementedTypes(list);
    }
  }

  private void setFields() {
    ClassOrInterfaceType type = new ClassOrInterfaceType();
    ClassOrInterfaceType arg = new ClassOrInterfaceType();
    arg.setName(get(AstBases.class).get(METHOD_NODE).getType(0).getName());
    type.setName("java.util.List");
    type.setTypeArguments(arg);

    VariableDeclarator variableDeclarator = new VariableDeclarator();
    variableDeclarator.setName("methodNodes$");
    variableDeclarator.setType(type);

    FieldDeclaration fieldDeclaration = new FieldDeclaration();
    fieldDeclaration.setPublic(true);
    fieldDeclaration.setVariables(new NodeList<>(variableDeclarator));

    decl.addMember(fieldDeclaration);
  }
}
