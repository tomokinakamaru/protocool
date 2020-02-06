package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.WildcardType;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.WildcardContext;
import com.github.tomokinakamaru.protocool.context.ReferenceTypes;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;

public class EncodeReferenceContexts extends FileContextAnalysis {

  @Override
  public void initialize() {
    set(new ReferenceTypes());
  }

  @Override
  public void exitReference(ReferenceContext ctx) {
    ReferenceType r = encode(ctx);
    for (int i = 0; i < ctx.ARRAY().size(); i++) {
      r = new ArrayType(r);
    }
    get(ReferenceTypes.class).put(ctx, r);
  }

  private ReferenceType encode(ReferenceContext ctx) {
    ClassOrInterfaceType c = new ClassOrInterfaceType();
    c.setName(ctx.qualifiedName().getText());
    encode(ctx.children).ifNonEmpty(c::setTypeArguments);
    return c;
  }

  private NodeList<Type> encode(List<ParseTree> lst) {
    NodeList<Type> nodes = new NodeList<>();
    for (ParseTree t : lst) {
      if (t instanceof ReferenceContext) {
        nodes.add(encodeArgument((ReferenceContext) t));
      } else if (t instanceof WildcardContext) {
        nodes.add(encodeArgument((WildcardContext) t));
      }
    }
    return nodes;
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
