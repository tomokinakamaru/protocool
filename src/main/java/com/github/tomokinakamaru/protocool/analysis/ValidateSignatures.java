package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.context.TypeTables;
import com.github.tomokinakamaru.protocool.data.Automaton;
import com.github.tomokinakamaru.protocool.data.State;
import com.github.tomokinakamaru.protocool.data.Transition;
import com.github.tomokinakamaru.protocool.error.SignatureConflict;
import com.github.tomokinakamaru.protocool.utility.StateAnalysis;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;

public class ValidateSignatures extends StateAnalysis {

  @Override
  protected void analyze(ClassContext ctx, Automaton automaton, State state) {
    Set<Transition> transitions = automaton.getTransitionsFrom(state);
    for (Transition t1 : transitions) {
      String s1 = buildSignature(t1.symbol.asMethodContext());
      for (Transition t2 : transitions) {
        String s2 = buildSignature(t2.symbol.asMethodContext());
        if (t1 != t2 && s1.equals(s2)) {
          throw new SignatureConflict(s1);
        }
      }
    }
  }

  private String buildSignature(MethodContext ctx) {
    return ctx.name().getText() + "(" + buildSignature(ctx.argument()) + ")";
  }

  private String buildSignature(List<ArgumentContext> lst) {
    TypeTables tables = get(TypeTables.class);
    return lst.stream()
        .map(a -> tables.get(a.reference()).get(a.reference().qualifiedName().getText()))
        .map(this::buildSignature)
        .collect(Collectors.joining(","));
  }

  private String buildSignature(ParserRuleContext ctx) {
    if (ctx instanceof ParameterContext) {
      return "*";
    }
    if (ctx instanceof ClassContext) {
      return ((ClassContext) ctx).head().name().getText();
    }
    if (ctx instanceof ImportContext) {
      return ((ImportContext) ctx).qualifiedName().getText();
    }
    throw new RuntimeException();
  }
}
