package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.FactorContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.Listener;
import com.github.tomokinakamaru.protocool.antlr.StreamVisitor;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;

public class CreateClassNodeFields extends FileContextAnalysis {

  @Override
  public void enterClass(ClassContext ctx) {
    String groupName = ctx.head().name().getText();
    Map<String, Set<ChainContext>> chains = run(new CollectChains(ctx)).chains;
    for (String name : chains.keySet()) {
      CompilationUnit unit = get(ClassNodes.class).get(groupName).get(name);
      ClassOrInterfaceDeclaration decl = unit.getType(0).asClassOrInterfaceDeclaration();
      Methods methods = build(chains.get(name));

      for (String n : methods.keySet()) {
        boolean multiple = methods.get(n);
        CompilationUnit methodNode = get(MethodNodes.class).get(groupName).get(n);

        if (multiple) {
          ClassOrInterfaceType t = new ClassOrInterfaceType();
          t.setName(methodNode.getType(0).getName());

          ClassOrInterfaceType l = new ClassOrInterfaceType();
          l.setName("java.util.List");
          l.setTypeArguments(new NodeList<>(t));

          VariableDeclarator v = new VariableDeclarator();
          v.setType(l);
          v.setName(methodNode.getType(0).getName());

          FieldDeclaration f = new FieldDeclaration();
          f.setPublic(true);
          f.setVariables(new NodeList<>(v));
          decl.addMember(f);

        } else {
          ClassOrInterfaceType t = new ClassOrInterfaceType();
          t.setName(methodNode.getType(0).getName());

          VariableDeclarator v = new VariableDeclarator();
          v.setType(t);
          v.setName(methodNode.getType(0).getName());

          FieldDeclaration f = new FieldDeclaration();
          f.setPublic(true);
          f.setVariables(new NodeList<>(v));
          decl.addMember(f);
        }
      }
    }
  }

  private Methods build(Set<ChainContext> chains) {
    Methods methods = new Methods();
    for (ChainContext c : chains) {
      Methods m = run(new CollectMethods(c)).methods;
      run(new MarkMultipleOccurrence(c, m));
      for (String name : m.keySet()) {
        if (methods.containsKey(name)) {
          if (!methods.get(name) && m.get(name)) {
            methods.put(name, true);
          }
        } else {
          methods.put(name, m.get(name));
        }
      }
    }
    return methods;
  }

  private static final class Methods extends HashMap<String, Boolean> {}

  private static final class MarkMultipleOccurrence extends Listener {

    private final ChainContext context;

    private final Methods methods;

    private final Set<String> names = new HashSet<>();

    private MarkMultipleOccurrence(ChainContext context, Methods methods) {
      this.context = context;
      this.methods = methods;
    }

    @Override
    protected ParserRuleContext getContext() {
      return context;
    }

    @Override
    public void enterMethod(MethodContext ctx) {
      String name = ctx.name().getText();
      if (names.contains(name)) {
        methods.put(name, true);
      }
      names.add(name);
    }

    @Override
    public void enterFactor(FactorContext ctx) {
      if (ctx.REPEAT0() != null || ctx.REPEAT1() != null) {
        for (String n : run(new CollectMethods(ctx)).methods.keySet()) {
          methods.put(n, true);
        }
      }
    }
  }

  private static final class CollectMethods extends StreamVisitor<String> {

    private final ParserRuleContext context;

    private final Methods methods = new Methods();

    private CollectMethods(ParserRuleContext context) {
      this.context = context;
    }

    @Override
    protected ParserRuleContext getContext() {
      return context;
    }

    @Override
    protected void analyze(Stream<String> result) {
      for (String name : result.collect(Collectors.toSet())) {
        methods.put(name, false);
      }
    }

    @Override
    public Stream<String> visitMethod(MethodContext ctx) {
      return Stream.of(ctx.name().getText());
    }
  }

  private static final class CollectChains extends StreamVisitor<ChainContext> {

    private final ClassContext context;

    private final Map<String, Set<ChainContext>> chains = new HashMap<>();

    private CollectChains(ClassContext context) {
      this.context = context;
    }

    @Override
    protected ParserRuleContext getContext() {
      return context;
    }

    @Override
    protected void analyze(Stream<ChainContext> result) {
      for (ChainContext ctx : result.collect(Collectors.toSet())) {
        String key = ctx.reference().qualifiedName().getText();
        chains.putIfAbsent(key, new HashSet<>());
        chains.get(key).add(ctx);
      }
    }

    @Override
    public Stream<ChainContext> visitChain(ChainContext ctx) {
      return Stream.of(ctx);
    }
  }
}
