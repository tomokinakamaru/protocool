package com.github.tomokinakamaru.protocool.analysis.automaton;

import static com.github.tomokinakamaru.antlr4.utility.NodeFinder.findParent;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.AutomatonAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationBaseVisitor;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Symbol;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;

public class PropagateParameter extends AutomatonAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a) {
    List<State> queue = new ArrayList<>();
    State initial = a.initials.stream().findAny().orElseThrow(RuntimeException::new);

    initial.parameters = new LinkedHashSet<>(ctx.head().parameter());
    queue.add(initial);

    while (!queue.isEmpty()) {
      State src = queue.remove(0);
      for (Transition transition : a.getTransitionsFrom(src)) {
        Symbol symbol = transition.symbol;
        if (symbol.isReferenceContext()) {
          continue;
        }

        Set<ParameterContext> srcParams = new LinkedHashSet<>(src.parameters);
        Set<ParameterContext> symParams = getParameters(symbol.asMethodContext());

        Set<ParameterContext> srcSymParams = new LinkedHashSet<>();
        srcSymParams.addAll(srcParams);
        srcSymParams.addAll(symParams);

        State dst = transition.destination;
        if (dst.parameters != null) {
          if (!dst.parameters.equals(srcSymParams)) {
            if (symParams.isEmpty()) {
              dst.parameters = srcSymParams;
            } else {
              State state = clone(a, dst);
              state.parameters = srcSymParams;
              a.transitions.add(new Transition(src, symbol, state));
              a.transitions.remove(transition);
              queue.add(state);
            }
          }
        } else {
          dst.parameters = srcSymParams;
          queue.add(dst);
        }
      }
    }
  }

  private State clone(Automaton automaton, State state) {
    State s = new State();
    for (Transition t : automaton.getTransitionsFrom(state)) {
      State dst = t.destination;
      if (dst.equals(t.source)) {
        dst = s;
      }
      automaton.transitions.add(new Transition(s, t.symbol, dst));
    }
    return s;
  }

  private Set<ParameterContext> getParameters(MethodContext ctx) {
    return new Visitor().visit(ctx).collect(Collectors.toCollection(LinkedHashSet::new));
  }

  private final class Visitor extends SpecificationBaseVisitor<Stream<ParameterContext>> {

    @Override
    public Stream<ParameterContext> visitReference(ReferenceContext ctx) {
      TypeTable table = get(TypeTables.class).get(findParent(ClassContext.class, ctx));
      ParserRuleContext c = table.get(ctx.qualifiedName().getText());
      if (c instanceof ParameterContext) {
        return Stream.concat(Stream.of((ParameterContext) c), visitChildren(ctx));
      } else {
        return visitChildren(ctx);
      }
    }

    @Override
    protected Stream<ParameterContext> aggregateResult(
        Stream<ParameterContext> aggregate, Stream<ParameterContext> nextResult) {
      return Stream.concat(aggregate, nextResult);
    }

    @Override
    protected Stream<ParameterContext> defaultResult() {
      return Stream.empty();
    }
  }
}
