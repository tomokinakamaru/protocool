package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.automaton.AnnotateStatic;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignEvaluator;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateNumber;
import com.github.tomokinakamaru.protocool.analysis.automaton.AssignStateReference;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildClazzAutomaton;
import com.github.tomokinakamaru.protocool.analysis.automaton.BuildExpressionAutomaton;
import com.github.tomokinakamaru.protocool.analysis.automaton.FindConflict;
import com.github.tomokinakamaru.protocool.analysis.automaton.PropagateParameter;
import com.github.tomokinakamaru.protocool.analysis.automaton.RemoveFinalStates;
import com.github.tomokinakamaru.protocool.analysis.misc.AssignPackage;
import com.github.tomokinakamaru.protocool.analysis.misc.AssignParameters;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildClassNodeBaseName;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildClassNodeName;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildClassNodeNameTable;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildClassNodeTable;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildMethodNodeBaseName;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildMethodNodeName;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildMethodNodeNameTable;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildMethodNodeNames;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildMethodNodeTable;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildNormalizedText;
import com.github.tomokinakamaru.protocool.analysis.misc.BuildSignature;
import com.github.tomokinakamaru.protocool.analysis.skeleton.FormatSkeleton;
import com.github.tomokinakamaru.protocool.analysis.skeleton.GenerateApiSkeleton;
import com.github.tomokinakamaru.protocool.analysis.skeleton.GenerateBaseFile;
import com.github.tomokinakamaru.protocool.analysis.skeleton.GenerateClassAstSkeleton;
import com.github.tomokinakamaru.protocool.analysis.skeleton.GenerateMethodAstSkeleton;
import com.github.tomokinakamaru.protocool.analysis.symbol.BuildClazzTable;
import com.github.tomokinakamaru.protocool.analysis.symbol.BuildForeignTypeTable;
import com.github.tomokinakamaru.protocool.analysis.symbol.BuildParameterTable;
import com.github.tomokinakamaru.protocool.analysis.symbol.ResolveReference;
import com.github.tomokinakamaru.protocool.analysis.syntax.AssignOwnerChain;
import com.github.tomokinakamaru.protocool.analysis.syntax.AssignOwnerClazz;
import com.github.tomokinakamaru.protocool.analysis.syntax.AssignOwnerSpecification;
import com.github.tomokinakamaru.protocool.parser.Lexer;
import com.github.tomokinakamaru.protocool.parser.Parser;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Protocool {

  public final List<Analysis> processes = new ArrayList<>(defaultProcesses());

  public SpecificationContext run(CharStream stream) {
    Lexer lexer = new Lexer(stream);
    Parser parser = new Parser(new CommonTokenStream(lexer));
    SpecificationContext ctx = parser.specification();
    processes.forEach(p -> p.run(ctx));
    return ctx;
  }

  private static List<Analysis> defaultProcesses() {
    return Arrays.asList(
        new AssignOwnerSpecification(),
        new AssignOwnerClazz(),
        new AssignOwnerChain(),
        new AssignPackage(),
        new BuildClazzTable(),
        new BuildParameterTable(),
        new BuildForeignTypeTable(),
        new ResolveReference(),
        new BuildNormalizedText(),
        new BuildSignature(),
        new BuildMethodNodeBaseName(),
        new BuildMethodNodeNameTable(),
        new BuildMethodNodeName(),
        new BuildMethodNodeTable(),
        new BuildClassNodeBaseName(),
        new BuildClassNodeNameTable(),
        new BuildClassNodeName(),
        new BuildClassNodeTable(),
        new BuildMethodNodeNames(),
        new AssignParameters(),
        new BuildExpressionAutomaton(),
        new AnnotateStatic(),
        new AssignEvaluator(),
        new BuildClazzAutomaton(),
        new PropagateParameter(),
        new AssignStateReference(),
        new RemoveFinalStates(),
        new FindConflict(),
        new AssignStateNumber(),
        new GenerateApiSkeleton(),
        new GenerateClassAstSkeleton(),
        new GenerateMethodAstSkeleton(),
        new GenerateBaseFile(),
        new FormatSkeleton());
  }
}
