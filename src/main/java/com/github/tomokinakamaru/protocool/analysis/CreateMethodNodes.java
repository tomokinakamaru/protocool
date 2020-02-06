package com.github.tomokinakamaru.protocool.analysis;

import static com.github.tomokinakamaru.protocool.analysis.CreateBaseNodes.METHOD_NODE;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;
import java.util.HashMap;

public class CreateMethodNodes extends FileContextAnalysis {

  private String groupName;

  @Override
  public void initialize() {
    set(new MethodNodes());
  }

  @Override
  public void enterClass(ClassContext ctx) {
    groupName = ctx.head().name().getText();
  }

  @Override
  public void enterMethod(MethodContext ctx) {
    String name = ctx.name().getText();
    get(MethodNodes.class).putIfAbsent(groupName, new HashMap<>());
    get(MethodNodes.class).get(groupName).put(name, create(name));
  }

  private CompilationUnit create(String name) {
    ClassOrInterfaceDeclaration decl = new ClassOrInterfaceDeclaration();
    decl.setPublic(true);

    decl.setName(String.format("%s$%s", groupName, name));

    ClassOrInterfaceType type = new ClassOrInterfaceType();
    type.setName(get(AstBases.class).get(METHOD_NODE).getType(0).getName());
    decl.setExtendedTypes(new NodeList<>(type));

    CompilationUnit unit = new CompilationUnit();
    unit.addType(decl);
    return unit;
  }
}
