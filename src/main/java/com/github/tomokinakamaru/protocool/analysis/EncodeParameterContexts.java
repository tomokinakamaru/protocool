package com.github.tomokinakamaru.protocool.analysis;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.context.ReferenceTypes;
import com.github.tomokinakamaru.protocool.context.TypeParameters;
import com.github.tomokinakamaru.protocool.utility.FileContextAnalysis;
import java.util.List;
import java.util.stream.Collectors;

public class EncodeParameterContexts extends FileContextAnalysis {

  @Override
  public void initialize() {
    set(new TypeParameters());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    get(TypeParameters.class).put(ctx, encode(ctx));
  }

  private TypeParameter encode(ParameterContext ctx) {
    TypeParameter t = new TypeParameter(ctx.name().getText());
    encode(ctx.reference()).ifNonEmpty(t::setTypeBound);
    return t;
  }

  private NodeList<ClassOrInterfaceType> encode(List<ReferenceContext> lst) {
    return lst.stream().map(this::encode).collect(Collectors.toCollection(NodeList::new));
  }

  private ClassOrInterfaceType encode(ReferenceContext ctx) {
    return get(ReferenceTypes.class).get(ctx).asClassOrInterfaceType();
  }
}
