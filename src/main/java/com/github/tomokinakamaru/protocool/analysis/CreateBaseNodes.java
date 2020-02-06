package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.utility.antlr4.AbstractAnalyzer;

public class CreateBaseNodes extends AbstractAnalyzer {

  static final Object METHOD_NODE = new Object();

  static final Object CLASS_NODE = new Object();

  private static final Object NODE = new Object();

  @Override
  public void initialize() {
    set(new AstBases());
  }

  @Override
  public void analyze() {
    get(AstBases.class).put(NODE, createNode());
    get(AstBases.class).put(CLASS_NODE, createClassNode());
    get(AstBases.class).put(METHOD_NODE, createMethodNode());
  }

  private CompilationUnit createNode() {
    ClassOrInterfaceDeclaration decl = createDeclaration("AbstractNode");
    return createCompilationUnit(decl);
  }

  private CompilationUnit createMethodNode() {
    ClassOrInterfaceDeclaration decl = createDeclaration("AbstractMethodNode");
    decl.setExtendedTypes(createExtendClause());
    return createCompilationUnit(decl);
  }

  private CompilationUnit createClassNode() {
    ClassOrInterfaceDeclaration decl = createDeclaration("AbstractClassNode");
    decl.setExtendedTypes(createExtendClause());
    return createCompilationUnit(decl);
  }

  private ClassOrInterfaceDeclaration createDeclaration(String name) {
    ClassOrInterfaceDeclaration decl = new ClassOrInterfaceDeclaration();
    decl.setPublic(true);
    decl.setAbstract(true);
    decl.setName(name);
    return decl;
  }

  private NodeList<ClassOrInterfaceType> createExtendClause() {
    NodeList<ClassOrInterfaceType> ext = new NodeList<>();
    ClassOrInterfaceType e = new ClassOrInterfaceType();
    e.setName(get(AstBases.class).get(NODE).getType(0).getName());
    ext.add(e);
    return ext;
  }

  private CompilationUnit createCompilationUnit(ClassOrInterfaceDeclaration decl) {
    CompilationUnit unit = new CompilationUnit();
    unit.addType(decl);
    return unit;
  }
}
