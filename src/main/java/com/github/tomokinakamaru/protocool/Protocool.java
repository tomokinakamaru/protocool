package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.antlr4.utility.AbstractAnalyzer;
import com.github.tomokinakamaru.antlr4.utility.AbstractCompiler;
import com.github.tomokinakamaru.antlr4.utility.Context;
import com.github.tomokinakamaru.protocool.analysis.BuildSkeletons;
import com.github.tomokinakamaru.protocool.analysis.BuildTypeTables;
import com.github.tomokinakamaru.protocool.analysis.Parse;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateNumbers;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateReferences;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildChainAutomatons;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildClassAutomatons;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildNormalForms;
import com.github.tomokinakamaru.protocool.analysis.automaton.FindReturnExpressions;
import com.github.tomokinakamaru.protocool.analysis.automaton.FindStaticMethods;
import com.github.tomokinakamaru.protocool.analysis.automaton.PropagateParameters;
import com.github.tomokinakamaru.protocool.analysis.automaton.RemoveFinalStates;
import com.github.tomokinakamaru.protocool.analysis.automaton.ValidateReturnTypes;
import com.github.tomokinakamaru.protocool.analysis.automaton.ValidateSignatures;
import com.github.tomokinakamaru.protocool.analysis.code.EncodeReferences;
import com.github.tomokinakamaru.protocool.analysis.code.EncodeStates;
import com.github.tomokinakamaru.protocool.analysis.code.EncodeTransitions;
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
        new BuildClassAutomatons(),
        new PropagateParameters(),
        new ValidateReturnTypes(),
        new AssignStateReferences(),
        new RemoveFinalStates(),
        new ValidateSignatures(),
        new AssignStateNumbers(),
        new EncodeReferences(),
        new EncodeStates(),
        new EncodeTransitions(),
        new BuildSkeletons());
  }
}
