package com.github.tomokinakamaru.protocool.analysis.code;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.WildcardType;
import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.WildcardContext;
import com.github.tomokinakamaru.protocool.data.code.ReferenceTypes;
import org.antlr.v4.runtime.tree.ParseTree;

public class EncodeReferences extends Listener {

  @Override
  public void initialize() {
    set(new ReferenceTypes());
  }

  @Override
  public void exitReference(ReferenceContext ctx) {
    ReferenceType t = encode(ctx);
    for (int i = 0; i < ctx.ARRAY().size(); i++) {
      t = new ArrayType(t);
    }
    get(ReferenceTypes.class).put(ctx, t);
  }

  private ClassOrInterfaceType encode(ReferenceContext ctx) {
    ClassOrInterfaceType type = new ClassOrInterfaceType();
    type.setName(ctx.qualifiedName().getText());
    NodeList<Type> list = encodeArguments(ctx);
    if (list.size() != 0) {
      type.setTypeArguments(encodeArguments(ctx));
    }
    return type;
  }

  private NodeList<Type> encodeArguments(ReferenceContext ctx) {
    NodeList<Type> list = new NodeList<>();
    for (ParseTree c : ctx.children) {
      if (c instanceof ReferenceContext) {
        list.add(encodeArgument((ReferenceContext) c));
      } else if (c instanceof WildcardContext) {
        list.add(encodeArgument((WildcardContext) c));
      }
    }
    return list;
  }

  private ReferenceType encodeArgument(ReferenceContext ctx) {
    return get(ReferenceTypes.class).get(ctx);
  }

  private Type encodeArgument(WildcardContext ctx) {
    WildcardType t = new WildcardType();
    if (ctx.reference() != null) {
      if (ctx.SUPER() == null) {
        t.setExtendedType(encodeArgument(ctx.reference()));
      } else {
        t.setSuperType(encodeArgument(ctx.reference()));
      }
    }
    return t;
  }
}
