package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4.utility.AbstractCompiler;
import com.github.tomokinakamaru.antlr4.utility.Context;
import com.github.tomokinakamaru.protocool.analysis.code.BuildSkeletons;
import com.github.tomokinakamaru.protocool.analysis.BuildTypeTables;
import com.github.tomokinakamaru.protocool.analysis.Parse;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateNumber;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateReference;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildChainAutomatons;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildClassAutomaton;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildNormalForms;
import com.github.tomokinakamaru.protocool.analysis.automaton.FindReturnExpressions;
import com.github.tomokinakamaru.protocool.analysis.automaton.FindStaticMethods;
import com.github.tomokinakamaru.protocool.analysis.automaton.PropagateParameters;
import com.github.tomokinakamaru.protocool.analysis.automaton.RemoveFinalState;
import com.github.tomokinakamaru.protocool.analysis.automaton.ValidateReturnType;
import com.github.tomokinakamaru.protocool.analysis.automaton.ValidateSignature;
import com.github.tomokinakamaru.protocool.analysis.code.EncodeState;
import com.github.tomokinakamaru.protocool.analysis.code.EncodeTransition;
import com.github.tomokinakamaru.protocool.data.skeleton.Skeletons;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;

public class Protocool extends AbstractCompiler {

  public Skeletons run(CharStream charStream) {
    Context context = new Context();
    context.set(charStream);
    compile(context);
    return context.get(Skeletons.class);
  }

  @Override
  protected List<AbstractAnalyzer> analyzers() {
    return Arrays.asList(
        new Parse(),
        new BuildTypeTables(),
        new BuildNormalForms(),
        new BuildChainAutomatons(),
        new FindStaticMethods(),
        new FindReturnExpressions(),
        new BuildNormalForms(),
        new BuildClassAutomaton(),
        new PropagateParameters(),
        new ValidateReturnType(),
        new AssignStateReference(),
        new RemoveFinalState(),
        new ValidateSignature(),
        new AssignStateNumber(),
        new EncodeState(),
        new EncodeTransition(),
        new BuildSkeletons());
  }
}
