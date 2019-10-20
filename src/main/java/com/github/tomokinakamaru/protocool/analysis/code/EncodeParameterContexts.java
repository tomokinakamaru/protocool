package com.github.tomokinakamaru.protocool.analysis.code;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.tomokinakamaru.protocool.analysis.abst.Listener;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.code.ClassTypes;
import com.github.tomokinakamaru.protocool.data.code.TypeParameters;
import java.util.List;
import java.util.stream.Collectors;

public class EncodeParameterContexts extends Listener {

  @Override
  public void initialize() {
    set(new TypeParameters());
  }

  @Override
  public void enterParameter(ParameterContext ctx) {
    TypeParameter p = new TypeParameter();
    p.setName(ctx.name().getText());
    p.setTypeBound(encodeBounds(ctx.reference()));
    get(TypeParameters.class).put(ctx, p);
  }

  private NodeList<ClassOrInterfaceType> encodeBounds(List<ReferenceContext> list) {
    return list.stream()
        .map(c -> get(ClassTypes.class).get(c).asClassOrInterfaceType())
        .collect(Collectors.toCollection(NodeList::new));
  }
}
