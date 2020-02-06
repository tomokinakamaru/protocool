package com.github.tomokinakamaru.protocool;

import com.github.javaparser.ast.CompilationUnit;
import com.github.tomokinakamaru.protocool.analysis.AssignStateNumbers;
import com.github.tomokinakamaru.protocool.analysis.AssignStateReferences;
import com.github.tomokinakamaru.protocool.analysis.BuildChainAutomatons;
import com.github.tomokinakamaru.protocool.analysis.BuildClassAutomatons;
import com.github.tomokinakamaru.protocool.analysis.BuildNormalForms;
import com.github.tomokinakamaru.protocool.analysis.BuildTypeTables;
import com.github.tomokinakamaru.protocool.analysis.CreateBaseNodes;
import com.github.tomokinakamaru.protocool.analysis.CreateClassNodeAcceptors;
import com.github.tomokinakamaru.protocool.analysis.CreateClassNodeFields;
import com.github.tomokinakamaru.protocool.analysis.CreateClassNodes;
import com.github.tomokinakamaru.protocool.analysis.CreateMethodNodeAcceptors;
import com.github.tomokinakamaru.protocool.analysis.CreateMethodNodeFields;
import com.github.tomokinakamaru.protocool.analysis.CreateMethodNodes;
import com.github.tomokinakamaru.protocool.analysis.CreateVisitor;
import com.github.tomokinakamaru.protocool.analysis.EncodeParameterContexts;
import com.github.tomokinakamaru.protocool.analysis.EncodeReferenceContexts;
import com.github.tomokinakamaru.protocool.analysis.EncodeStates;
import com.github.tomokinakamaru.protocool.analysis.EncodeTransitions;
import com.github.tomokinakamaru.protocool.analysis.FindReturnExpressions;
import com.github.tomokinakamaru.protocool.analysis.FindStaticMethods;
import com.github.tomokinakamaru.protocool.analysis.Parse;
import com.github.tomokinakamaru.protocool.analysis.PropagateParameters;
import com.github.tomokinakamaru.protocool.analysis.RebuildNormalForms;
import com.github.tomokinakamaru.protocool.analysis.RemoveFinalStates;
import com.github.tomokinakamaru.protocool.analysis.SetPackages;
import com.github.tomokinakamaru.protocool.analysis.ValidateReturnTypes;
import com.github.tomokinakamaru.protocool.analysis.ValidateSignatures;
import com.github.tomokinakamaru.protocool.context.ApiClasses;
import com.github.tomokinakamaru.protocool.context.AstBases;
import com.github.tomokinakamaru.protocool.context.ClassNodes;
import com.github.tomokinakamaru.protocool.context.MethodNodes;
import com.github.tomokinakamaru.utility.antlr4.AbstractAnalyzer;
import com.github.tomokinakamaru.utility.antlr4.AbstractCompiler;
import com.github.tomokinakamaru.utility.antlr4.Context;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.antlr.v4.runtime.CharStream;

public class Protocool extends AbstractCompiler {

  public Collection<CompilationUnit> run(CharStream charStream) {
    Context context = new Context();
    context.set(charStream);
    compile(context);

    Collection<CompilationUnit> units = new HashSet<>();
    units.addAll(context.get(ApiClasses.class).values());
    units.addAll(context.get(AstBases.class).values());
    units.addAll(context.get(ClassNodes.class).getCompilationUnits());
    units.addAll(context.get(MethodNodes.class).getCompilationUnits());
    units.add(CreateVisitor.VISITOR);
    return units;
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
        new RebuildNormalForms(),
        new BuildClassAutomatons(),
        new PropagateParameters(),
        new ValidateReturnTypes(),
        new AssignStateReferences(),
        new RemoveFinalStates(),
        new ValidateSignatures(),
        new AssignStateNumbers(),
        new EncodeReferenceContexts(),
        new EncodeParameterContexts(),
        new CreateBaseNodes(),
        new CreateClassNodes(),
        new CreateMethodNodes(),
        new CreateMethodNodeFields(),
        new CreateClassNodeFields(),
        new CreateVisitor(),
        new CreateClassNodeAcceptors(),
        new CreateMethodNodeAcceptors(),
        new EncodeStates(),
        new EncodeTransitions(),
        new SetPackages());
  }
}
