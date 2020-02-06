package com.github.tomokinakamaru.protocool.analysis;

import static com.github.tomokinakamaru.protocool.analysis.CreateBaseNodes.CLASS_NODE;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.protocool.context.TypeParameters;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateClassNodes extends FileContextAnalysis {

  private String groupName;

  @Override
  public void initialize() {
    set(new ClassNodes());
  }

  @Override
  public void enterClass(ClassContext ctx) {
    groupName = ctx.head().name().getText();
  }

  @Override
  public void enterChain(ChainContext ctx) {
    String name = ctx.reference().qualifiedName().getText();
    Set<ParameterContext> params = run(new CollectParameters(ctx.reference())).parameters;
    get(ClassNodes.class).putIfAbsent(groupName, new HashMap<>());
    get(ClassNodes.class).get(groupName).put(name, create(name, params));
  }

  private CompilationUnit create(String name, Set<ParameterContext> params) {
    ClassOrInterfaceDeclaration decl = new ClassOrInterfaceDeclaration();
    decl.setPublic(true);

    decl.setName(String.format("%s$%s", groupName, name.replace(".", "_")));

    params
        .stream()
        .map(p -> get(TypeParameters.class).get(p))
        .collect(Collectors.toCollection(NodeList::new))
        .ifNonEmpty(decl::setTypeParameters);

    ClassOrInterfaceType type = new ClassOrInterfaceType();
    type.setName(get(AstBases.class).get(CLASS_NODE).getType(0).getName());
    decl.setExtendedTypes(new NodeList<>(type));

    CompilationUnit unit = new CompilationUnit();
    unit.addType(decl);
    return unit;
  }
}
