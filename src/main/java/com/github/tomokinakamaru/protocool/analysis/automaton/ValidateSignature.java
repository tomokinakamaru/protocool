package com.github.tomokinakamaru.protocool.analysis.automaton;

import static com.github.tomokinakamaru.protocool.analysis.Utility.findClassContext;

import com.github.tomokinakamaru.protocool.analysis.abst.automaton.StateAnalyzer;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import com.github.tomokinakamaru.protocool.data.automaton.Transition;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTable;
import com.github.tomokinakamaru.protocool.data.typetable.TypeTables;
import com.github.tomokinakamaru.protocool.error.SignatureConflict;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;

public class ValidateSignature extends StateAnalyzer {

  @Override
  protected void analyze(ClassContext ctx, Automaton a, State s) {
    Set<Transition> transitions = a.getTransitionsFrom(s);
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
    TypeTable table = get(TypeTables.class).get(findClassContext(ctx));
    return ctx.name().getText() + "(" + buildSignature(ctx.argument(), table) + ")";
  }

  private String buildSignature(List<ArgumentContext> lst, TypeTable table) {
    return lst.stream()
        .map(a -> table.get(a.reference().qualifiedName().getText()))
        .map(this::buildSignature)
        .collect(Collectors.joining(","));
  }

  private String buildSignature(ParserRuleContext ctx) {
    if (ctx instanceof ParameterContext) {
      return ((ParameterContext) ctx).name().getText();
    } else if (ctx instanceof ClassContext) {
      return ((ClassContext) ctx).head().name().getText();
    } else if (ctx instanceof ImportContext) {
      return ((ImportContext) ctx).qualifiedName().getText();
    } else {
      throw new RuntimeException();
    }
  }
}
