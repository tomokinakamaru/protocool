package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.antlr.StreamVisitor;
import com.github.tomokinakamaru.protocool.context.TypeTables;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;

final class CollectParameters extends StreamVisitor<ParameterContext> {

  private final ParserRuleContext context;

  Set<ParameterContext> parameters;

  CollectParameters(ParserRuleContext ctx) {
    this.context = ctx;
  }

  @Override
  protected ParserRuleContext getContext() {
    return context;
  }

  @Override
  protected void analyze(Stream<ParameterContext> result) {
    parameters = result.collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Override
  public Stream<ParameterContext> visitReference(ReferenceContext ctx) {
    String name = ctx.qualifiedName().getText();
    if (get(TypeTables.class).get(ctx).has(name)) {
      ParserRuleContext c = get(TypeTables.class).get(ctx).get(name);
      if (c instanceof ParameterContext) {
        return Stream.concat(Stream.of((ParameterContext) c), visitChildren(ctx));
      } else {
        return visitChildren(ctx);
      }
    } else {
      return Stream.empty();
    }
  }
}
