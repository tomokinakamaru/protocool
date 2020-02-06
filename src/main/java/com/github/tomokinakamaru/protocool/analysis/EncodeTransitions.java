package com.github.tomokinakamaru.protocool.analysis;

import static com.github.javaparser.ast.expr.BinaryExpr.Operator.EQUALS;
import static com.github.tomokinakamaru.protocool.analysis.CreateBaseNodes.METHOD_NODE;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.context.ApiClasses;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.protocool.context.ReferenceTypes;
import com.github.tomokinakamaru.protocool.context.ReturnExpressions;
import com.github.tomokinakamaru.protocool.context.StaticMethods;
import com.github.tomokinakamaru.protocool.context.TypeParameters;
import com.github.tomokinakamaru.protocool.context.TypeTables;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.data.TypeTable;
import com.github.tomokinakamaru.protocool.utility.TransitionAnalysis;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EncodeTransitions extends TransitionAnalysis {

  private MethodDeclaration decl;

  private Transition transition;

  private String groupName;

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton, Transition transition) {
    groupName = ctx.head().name().getText();
    this.transition = transition;
    decl = new MethodDeclaration();
    decl.setPublic(true);

    setStatic();
    setParameters();
    setReturnType();
    setName();
    setArguments();
    setBody();

    get(ApiClasses.class)
        .get(transition.source)
        .getType(0)
        .asClassOrInterfaceDeclaration()
        .addMember(decl);
  }

  private void setStatic() {
    if (get(StaticMethods.class).contains(transition.symbol.asMethodContext())) {
      decl.setStatic(true);
    }
  }

  private void setParameters() {
    if (!transition.source.parameters.equals(transition.destination.parameters)) {
      Set<ParameterContext> ps = new LinkedHashSet<>(transition.destination.parameters);
      ps.removeAll(transition.source.parameters);
      for (ParameterContext p : ps) {
        decl.addTypeParameter(get(TypeParameters.class).get(p));
      }
    }
  }

  private void setReturnType() {
    decl.setType(getReturnType());
  }

  private void setName() {
    decl.setName(transition.symbol.asMethodContext().name().getText());
  }

  private void setArguments() {
    MethodContext m = transition.symbol.asMethodContext();
    for (ArgumentContext a : m.argument()) {
      decl.addParameter(get(ReferenceTypes.class).get(a.reference()), a.name().getText());
    }
  }

  private void setBody() {
    BlockStmt block = new BlockStmt();
    addMethodNodeCreation(block);
    addReturnExpression(block);
    decl.setBody(block);
  }

  private void addMethodNodeCreation(BlockStmt block) {
    // Initializer
    String name = transition.symbol.asMethodContext().name().getText();
    ClassOrInterfaceDeclaration decl =
        get(MethodNodes.class).get(groupName).get(name).getType(0).asClassOrInterfaceDeclaration();

    ObjectCreationExpr objectCreationExpr = new ObjectCreationExpr();
    ClassOrInterfaceType type = new ClassOrInterfaceType();
    type.setName(decl.getName());
    decl.getTypeParameters()
        .stream()
        .map(TypeParameter::getName)
        .map(
            n -> {
              ClassOrInterfaceType t = new ClassOrInterfaceType();
              t.setName(n);
              return (Type) t;
            })
        .collect(Collectors.toCollection(NodeList::new))
        .ifNonEmpty(type::setTypeArguments);
    objectCreationExpr.setType(type);

    VariableDeclarator variableDeclarator = new VariableDeclarator();
    variableDeclarator.setName("methodNode$");
    variableDeclarator.setType(type);
    variableDeclarator.setInitializer(objectCreationExpr);

    VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr();
    variableDeclarationExpr.setVariables(new NodeList<>(variableDeclarator));

    block.addStatement(variableDeclarationExpr);

    // Set fields
    for (ArgumentContext a : transition.symbol.asMethodContext().argument()) {
      FieldAccessExpr fieldAccessExpr = new FieldAccessExpr();
      fieldAccessExpr.setName(a.name().getText());
      fieldAccessExpr.setScope(new NameExpr("methodNode$"));

      AssignExpr assignExpr = new AssignExpr();
      assignExpr.setTarget(fieldAccessExpr);
      assignExpr.setValue(new NameExpr(a.name().getText()));

      block.addStatement(assignExpr);
    }

    // Add node to list
    if (get(StaticMethods.class).contains(transition.symbol.asMethodContext())) {
      ClassOrInterfaceType t = new ClassOrInterfaceType();
      ClassOrInterfaceType a = new ClassOrInterfaceType();
      a.setName(get(AstBases.class).get(METHOD_NODE).getType(0).getName());
      t.setName("java.util.ArrayList");
      t.setTypeArguments(a);

      VariableDeclarator varDecl = new VariableDeclarator();
      varDecl.setName("methodNodes$");
      varDecl.setType(t);

      ObjectCreationExpr e = new ObjectCreationExpr();
      e.setType(t);
      varDecl.setInitializer(e);
      block.addStatement(new VariableDeclarationExpr(varDecl));
    }

    MethodCallExpr methodCallExpr = new MethodCallExpr();
    methodCallExpr.setName("add");
    methodCallExpr.setScope(new NameExpr("methodNodes$"));
    methodCallExpr.setArguments(new NodeList<>(new NameExpr("methodNode$")));
    block.addStatement(methodCallExpr);
  }

  private void addReturnExpression(BlockStmt block) {
    String retExpr = get(ReturnExpressions.class).get(transition.symbol.asMethodContext());

    if (transition.destination.context == null || retExpr == null) {
      ObjectCreationExpr expr = new ObjectCreationExpr();
      expr.setType(getReturnType().asClassOrInterfaceType());

      VariableDeclarator variableDeclarator = new VariableDeclarator();
      variableDeclarator.setType(getReturnType().asClassOrInterfaceType());
      variableDeclarator.setName("state$");
      variableDeclarator.setInitializer(expr);

      block.addStatement(new VariableDeclarationExpr(variableDeclarator));

      FieldAccessExpr fieldAccessExpr = new FieldAccessExpr();
      fieldAccessExpr.setName("methodNodes$");
      fieldAccessExpr.setScope(new NameExpr("state$"));
      AssignExpr assignExpr = new AssignExpr();
      assignExpr.setTarget(fieldAccessExpr);
      assignExpr.setValue(new NameExpr("methodNodes$"));
      block.addStatement(assignExpr);

      block.addStatement(new ReturnStmt(new NameExpr("state$")));
      return;
    }

    // Class node construction
    ClassOrInterfaceDeclaration decl =
        get(ClassNodes.class)
            .get(groupName)
            .get(transition.destination.context.qualifiedName().getText())
            .getType(0)
            .asClassOrInterfaceDeclaration();
    ClassOrInterfaceType t = new ClassOrInterfaceType();
    t.setName(decl.getName());

    NodeList<Type> args = new NodeList<>();
    for (TypeParameter p : decl.getTypeParameters()) {
      ClassOrInterfaceType c = new ClassOrInterfaceType();
      c.setName(p.getName());
      args.add(c);
    }
    args.ifNonEmpty(t::setTypeArguments);

    ObjectCreationExpr objectCreationExpr = new ObjectCreationExpr();
    objectCreationExpr.setType(t);

    VariableDeclarator v = new VariableDeclarator();
    v.setType(t);
    v.setName("classNode$");
    v.setInitializer(objectCreationExpr);
    block.addStatement(new VariableDeclarationExpr(v));

    // Class node fields
    for (FieldDeclaration f : decl.getFields()) {
      VariableDeclarator vd = f.getVariable(0);
      ClassOrInterfaceType ty = vd.getType().asClassOrInterfaceType();
      if (ty.getName().asString().startsWith("java.util.List")) {
        Type a = ty.getTypeArguments().orElseThrow(RuntimeException::new).get(0);
        addClassNodeFields(
            block, a.asClassOrInterfaceType().getName().asString(), vd.getName().asString(), true);
      } else {
        addClassNodeFields(block, ty.getName().asString(), vd.getName().asString(), false);
      }
    }

    // To evaluator
    MethodCallExpr expr = new MethodCallExpr();
    expr.setName(retExpr);

    TypeTable table = get(TypeTables.class).get(transition.destination.context);
    String key = transition.destination.context.qualifiedName().getText();
    if (table.has(key) && table.get(key) instanceof ParameterContext) {
      expr.addArgument("methodNodes$");
    }

    expr.addArgument("classNode$");
    block.addStatement(new ReturnStmt(expr));
  }

  private void addClassNodeFields(BlockStmt block, String type, String name, boolean isList) {
    NameExpr buffer = new NameExpr("buffer$" + name);
    VariableDeclarator vd = new VariableDeclarator();
    vd.setName(buffer.getName());
    vd.setType(
        new ClassOrInterfaceType()
            .setName("java.util.ArrayList")
            .setTypeArguments(new ClassOrInterfaceType().setName(type)));
    vd.setInitializer(
        new ObjectCreationExpr()
            .setType(new ClassOrInterfaceType().setName("java.util.ArrayList").setTypeArguments()));
    block.addStatement(new VariableDeclarationExpr(vd));

    ForEachStmt forEachStmt = new ForEachStmt();
    forEachStmt.setVariable(
        new VariableDeclarationExpr()
            .addVariable(new VariableDeclarator().setName("node$").setType("AbstractMethodNode")));
    forEachStmt.setIterable(new NameExpr("methodNodes$"));

    IfStmt ifStmt = new IfStmt();
    ifStmt
        .setCondition(new InstanceOfExpr().setExpression(new NameExpr("node$")).setType(type))
        .setThenStmt(
            new BlockStmt()
                .addStatement(
                    new ExpressionStmt()
                        .setExpression(
                            new MethodCallExpr()
                                .setScope(buffer)
                                .setName("add")
                                .addArgument(
                                    new CastExpr().setExpression("node$").setType(type)))));
    forEachStmt.setBody(new BlockStmt().addStatement(ifStmt));

    block.addStatement(forEachStmt);

    AssignExpr assignExpr = new AssignExpr();
    assignExpr.setTarget(new FieldAccessExpr().setName(name).setScope(new NameExpr("classNode$")));
    if (isList) {
      assignExpr.setValue(buffer);
    } else {
      ConditionalExpr ce = new ConditionalExpr();
      ce.setCondition(
          new BinaryExpr()
              .setLeft(new MethodCallExpr().setScope(buffer).setName("size"))
              .setRight(new IntegerLiteralExpr(0))
              .setOperator(EQUALS));
      ce.setThenExpr(new NullLiteralExpr());
      ce.setElseExpr(new MethodCallExpr().setScope(buffer).setName("get").addArgument("0"));
      assignExpr.setValue(ce);
    }
    block.addStatement(assignExpr);
  }

  private ReferenceType getReturnType() {
    if (transition.destination.context != null) {
      return get(ReferenceTypes.class).get(transition.destination.context);
    } else {
      TypeDeclaration<?> t = get(ApiClasses.class).get(transition.destination).getType(0);
      ClassOrInterfaceDeclaration d = t.asClassOrInterfaceDeclaration();
      ClassOrInterfaceType r = new ClassOrInterfaceType();
      r.setName(t.getName().asString());

      NodeList<Type> types = new NodeList<>();
      for (TypeParameter p : d.getTypeParameters()) {
        ClassOrInterfaceType x = new ClassOrInterfaceType();
        x.setName(p.getName());
        types.add(x);
      }
      if (!types.isEmpty()) {
        r.setTypeArguments(types);
      }

      return r;
    }
  }
}
