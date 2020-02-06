package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.Listener;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.protocool.context.ReferenceTypes;
import com.github.tomokinakamaru.protocool.context.TypeParameters;
import com.github.tomokinakamaru.protocool.context.TypeTables;
import com.github.tomokinakamaru.protocool.data.TypeTable;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.antlr.v4.runtime.ParserRuleContext;

public class CreateMethodNodeFields extends FileContextAnalysis {

  @Override
  public void enterClass(ClassContext ctx) {
    String groupName = ctx.head().name().getText();
    Map<String, Set<ArgumentContext>> args = run(new CollectArgument(ctx)).args;

    for (String name : args.keySet()) {
      CompilationUnit unit = get(MethodNodes.class).get(groupName).get(name);
      ClassOrInterfaceDeclaration decl = unit.getType(0).asClassOrInterfaceDeclaration();

      Set<TypeParameter> typeParameters = new LinkedHashSet<>();
      Map<String, ReferenceType> fields = new LinkedHashMap<>();
      for (ArgumentContext a : args.get(name)) {
        String s = a.reference().qualifiedName().getText();
        ParserRuleContext c = get(TypeTables.class).get(a.reference()).get(s);
        if (c instanceof ParameterContext) {
          typeParameters.add(get(TypeParameters.class).get(c));
        }

        ReferenceType t = get(ReferenceTypes.class).get(a.reference());
        TypeTable table = get(TypeTables.class).get(a.reference());
        t.ifClassOrInterfaceType(
            x ->
                x.getTypeArguments()
                    .ifPresent(
                        l ->
                            l.forEach(
                                y -> {
                                  ParserRuleContext p = table.get(y.asString());
                                  TypeParameter tp = get(TypeParameters.class).get(p);
                                  typeParameters.add(tp);
                                })));
        fields.put(a.name().getText(), t);
      }
      decl.setTypeParameters(new NodeList<>(typeParameters));
      for (String s : fields.keySet()) {
        VariableDeclarator v = new VariableDeclarator();
        v.setType(fields.get(s));
        v.setName(s);
        FieldDeclaration f = new FieldDeclaration();
        f.setVariables(new NodeList<>(v));
        f.setPublic(true);
        decl.addMember(f);
      }
    }
  }

  private static final class CollectArgument extends Listener {

    private final ClassContext context;

    private final Map<String, Set<ArgumentContext>> args = new HashMap<>();

    private CollectArgument(ClassContext context) {
      this.context = context;
    }

    @Override
    protected ParserRuleContext getContext() {
      return context;
    }

    @Override
    public void enterMethod(MethodContext ctx) {
      String key = ctx.name().getText();
      args.putIfAbsent(key, new LinkedHashSet<>());
      args.get(key).addAll(ctx.argument());
    }
  }
}
