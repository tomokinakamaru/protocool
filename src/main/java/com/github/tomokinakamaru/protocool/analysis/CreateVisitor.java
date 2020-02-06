package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.VoidType;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.utility.antlr4.AbstractAnalyzer;
import java.util.stream.Collectors;

public class CreateVisitor extends AbstractAnalyzer {

  public static CompilationUnit VISITOR;

  @Override
  public void analyze() {
    VISITOR = new CompilationUnit();
    ClassOrInterfaceDeclaration decl = new ClassOrInterfaceDeclaration();
    VISITOR.addType(decl);

    decl.setPublic(true);
    decl.setInterface(true);
    decl.setName("Visitor");

    for (CompilationUnit unit : get(ClassNodes.class).getCompilationUnits()) {
      decl.addMember(create(unit));
    }

    for (CompilationUnit unit : get(MethodNodes.class).getCompilationUnits()) {
      decl.addMember(create(unit));
    }
  }

  public MethodDeclaration create(CompilationUnit unit) {
    ClassOrInterfaceDeclaration c = unit.getType(0).asClassOrInterfaceDeclaration();

    MethodDeclaration decl = new MethodDeclaration();
    decl.setName("visit");
    decl.setDefault(true);
    decl.setType(new VoidType());

    for (TypeParameter p : c.getTypeParameters()) {
      decl.addTypeParameter(p);
    }

    ClassOrInterfaceType t = new ClassOrInterfaceType().setName(c.getName());
    decl.getTypeParameters()
        .stream()
        .map(TypeParameter::getName)
        .map(n -> (Type) new ClassOrInterfaceType().setName(n))
        .collect(Collectors.toCollection(NodeList::new))
        .ifNonEmpty(t::setTypeArguments);

    decl.addParameter(t, "node");

    return decl;
  }
}
