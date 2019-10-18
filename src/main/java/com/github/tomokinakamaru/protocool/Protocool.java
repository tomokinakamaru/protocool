package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4.utility.AbstractCompiler;
import com.github.tomokinakamaru.antlr4.utility.Context;
import com.github.tomokinakamaru.protocool.analysis.BuildTypeTable;
import com.github.tomokinakamaru.protocool.analysis.Parse;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateNumber;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateReference;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildChainAutomaton;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildClassAutomaton;
import com.github.tomokinakamaru.protocool.analysis.automaton.FindConflict;
import com.github.tomokinakamaru.protocool.analysis.automaton.PropagateParameter;
import com.github.tomokinakamaru.protocool.analysis.automaton.RemoveFinalState;
import com.github.tomokinakamaru.protocool.analysis.automaton.StoreReturnExpression;
import com.github.tomokinakamaru.protocool.analysis.automaton.StoreStaticMethod;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;

public class Protocool extends AbstractCompiler {

  public void run(CharStream charStream) {
    Context context = new Context();
    context.set(charStream);
    compile(context);
  }

  @Override
  protected List<AbstractAnalyzer> analyses() {
    return Arrays.asList(
        new Parse(),
        new BuildTypeTable(),
        new BuildChainAutomaton(),
        new StoreStaticMethod(),
        new StoreReturnExpression(),
        new BuildClassAutomaton(),
        new PropagateParameter(),
        new AssignStateReference(),
        new RemoveFinalState(),
        new FindConflict(),
        new AssignStateNumber());
  }
}
