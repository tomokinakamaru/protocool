package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4.utility.AbstractCompiler;
import com.github.tomokinakamaru.antlr4.utility.Context;
import com.github.tomokinakamaru.protocool.analysis.Parse;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateNumber;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateReference;
import com.github.tomokinakamaru.protocool.analysis.automaton.PropagateParameters;
import com.github.tomokinakamaru.protocool.analysis.automaton.RemoveFinalState;
import com.github.tomokinakamaru.protocool.analysis.automaton.StoreReturnExpressions;
import com.github.tomokinakamaru.protocool.analysis.automaton.StoreStaticMethods;
import com.github.tomokinakamaru.protocool.analysis.automaton.ValidateReturnType;
import com.github.tomokinakamaru.protocool.analysis.automaton.ValidateSignature;
import com.github.tomokinakamaru.protocool.analysis.tree.BuildChainAutomaton;
import com.github.tomokinakamaru.protocool.analysis.tree.BuildClassAutomaton;
import com.github.tomokinakamaru.protocool.analysis.tree.BuildNormalForm;
import com.github.tomokinakamaru.protocool.analysis.tree.BuildTypeTable;
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
  protected List<AbstractAnalyzer> analyzers() {
    return Arrays.asList(
        new Parse(),
        new BuildTypeTable(),
        new BuildNormalForm(),
        new BuildChainAutomaton(),
        new StoreStaticMethods(),
        new StoreReturnExpressions(),
        new BuildNormalForm(),
        new BuildClassAutomaton(),
        new PropagateParameters(),
        new ValidateReturnType(),
        new AssignStateReference(),
        new RemoveFinalState(),
        new ValidateSignature(),
        new AssignStateNumber());
  }
}
