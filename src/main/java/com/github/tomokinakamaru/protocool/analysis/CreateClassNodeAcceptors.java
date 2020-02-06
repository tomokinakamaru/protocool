package com.github.tomokinakamaru.protocool.analysis;

import static com.github.javaparser.ast.expr.BinaryExpr.Operator.NOT_EQUALS;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.utility.antlr4.AbstractAnalyzer;

public class CreateClassNodeAcceptors extends AbstractAnalyzer {

  @Override
  public void analyze() {
    for (CompilationUnit unit : get(ClassNodes.class).getCompilationUnits()) {
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

    for (FieldDeclaration fd : decl.getFields()) {
      VariableDeclarator vd = fd.getVariable(0);
      NameExpr nameExpr = new NameExpr(vd.getName());
      IfStmt ifStmt = new IfStmt();
      ifStmt.setCondition(
          new BinaryExpr()
              .setLeft(nameExpr)
              .setRight(new NullLiteralExpr())
              .setOperator(NOT_EQUALS));

      BlockStmt blockStmt = new BlockStmt();

      if (vd.getType().asClassOrInterfaceType().getName().asString().startsWith("java.util.List")) {
        ForEachStmt forEachStmt = new ForEachStmt();
        forEachStmt.setVariable(
            new VariableDeclarationExpr()
                .setVariables(
                    new NodeList<>(
                        new VariableDeclarator()
                            .setName("e")
                            .setType(nameExpr.getName().asString()))));
        forEachStmt.setIterable(nameExpr);
        forEachStmt.setBody(
            new BlockStmt()
                .addStatement(
                    new MethodCallExpr()
                        .setScope(new NameExpr("e"))
                        .setName("accept")
                        .setArguments(new NodeList<>(new NameExpr("visitor")))));
        blockStmt.addStatement(forEachStmt);
      } else {
        blockStmt.addStatement(
            new MethodCallExpr()
                .setScope(nameExpr)
                .setName("accept")
                .setArguments(new NodeList<>(new NameExpr("visitor"))));
      }

      ifStmt.setThenStmt(blockStmt);
      body.addStatement(ifStmt);
    }

    md.setBody(body);

    decl.addMember(md);
  }
}
