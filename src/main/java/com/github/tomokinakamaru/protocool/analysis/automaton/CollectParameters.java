package com.github.tomokinakamaru.protocool.analysis.automaton;

import com.github.tomokinakamaru.protocool.analysis.abst.Visitor;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;

public class CollectParameters extends Visitor<ParameterContext> {

  private final MethodContext context;

  public Set<ParameterContext> parameters;

  public CollectParameters(MethodContext ctx) {
    this.context = ctx;
  }

  @Override
  protected MethodContext getContext() {
    return context;
  }

  @Override
  protected void analyze(Stream<ParameterContext> result) {
    parameters = result.collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Override
  public Stream<ParameterContext> visitReference(ReferenceContext ctx) {
    ParserRuleContext c = get(TypeTables.class).get(ctx).get(ctx.qualifiedName().getText());
    if (c instanceof ParameterContext) {
      return Stream.concat(Stream.of((ParameterContext) c), visitChildren(ctx));
    } else {
      return visitChildren(ctx);
    }
  }
}
