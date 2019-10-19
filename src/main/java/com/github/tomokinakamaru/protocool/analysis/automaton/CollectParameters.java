package com.github.tomokinakamaru.protocool.analysis.automaton;

import static com.github.tomokinakamaru.protocool.analysis.Utility.findClassContext;

import com.github.tomokinakamaru.protocool.analysis.abst.tree.StreamVisitor;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;

public class CollectParameters extends StreamVisitor<ParameterContext> {

  private final MethodContext context;

  Set<ParameterContext> parameters;

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
    TypeTable table = get(TypeTables.class).get(findClassContext(ctx));
    ParserRuleContext c = table.get(ctx.qualifiedName().getText());
    if (c instanceof ParameterContext) {
      return Stream.concat(Stream.of((ParameterContext) c), visitChildren(ctx));
    } else {
      return visitChildren(ctx);
    }
  }
}
