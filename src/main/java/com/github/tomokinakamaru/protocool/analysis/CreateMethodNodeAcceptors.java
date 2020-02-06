package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.utility.antlr4.AbstractAnalyzer;

public class CreateMethodNodeAcceptors extends AbstractAnalyzer {

  @Override
  public void analyze() {
    for (CompilationUnit unit : get(MethodNodes.class).getCompilationUnits()) {
      addAcceptor(unit.getType(0).asClassOrInterfaceDeclaration());
    }
  }

  private void addAcceptor(ClassOrInterfaceDeclaration decl) {
    MethodDeclaration md = new MethodDeclaration();
    md.setPublic(true);
    md.setName("accept");
    md.setType("void");
    md.addParameter("Visitor", "visitor");

    BlockStmt body = new BlockStmt();
    body.addStatement(
        new MethodCallExpr()
            .setScope(new NameExpr("visitor"))
            .setName("visit")
            .setArguments(new NodeList<>(new ThisExpr())));
    md.setBody(body);

    decl.addMember(md);
  }
}
