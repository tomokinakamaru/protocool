package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.analysis.AnnotateStatic;
import com.github.tomokinakamaru.protocool.analysis.AssignEvaluator;
import com.github.tomokinakamaru.protocool.analysis.AssignOwnerChain;
import com.github.tomokinakamaru.protocool.analysis.AssignOwnerClazz;
import com.github.tomokinakamaru.protocool.analysis.AssignOwnerSpecification;
import com.github.tomokinakamaru.protocool.analysis.AssignPackage;
import com.github.tomokinakamaru.protocool.analysis.AssignParameters;
import com.github.tomokinakamaru.protocool.analysis.AssignStateNumber;
import com.github.tomokinakamaru.protocool.analysis.AssignStateReference;
import com.github.tomokinakamaru.protocool.analysis.BuildClassNodeBaseName;
import com.github.tomokinakamaru.protocool.analysis.BuildClassNodeName;
import com.github.tomokinakamaru.protocool.analysis.BuildClassNodeNameTable;
import com.github.tomokinakamaru.protocool.analysis.BuildClassNodeTable;
import com.github.tomokinakamaru.protocool.analysis.BuildClazzAutomaton;
import com.github.tomokinakamaru.protocool.analysis.BuildClazzTable;
import com.github.tomokinakamaru.protocool.analysis.BuildExpressionAutomaton;
import com.github.tomokinakamaru.protocool.analysis.BuildForeignTypeTable;
import com.github.tomokinakamaru.protocool.analysis.BuildMethodNodeBaseName;
import com.github.tomokinakamaru.protocool.analysis.BuildMethodNodeName;
import com.github.tomokinakamaru.protocool.analysis.BuildMethodNodeNameTable;
import com.github.tomokinakamaru.protocool.analysis.BuildMethodNodeNames;
import com.github.tomokinakamaru.protocool.analysis.BuildMethodNodeTable;
import com.github.tomokinakamaru.protocool.analysis.BuildNormalizedText;
import com.github.tomokinakamaru.protocool.analysis.BuildParameterTable;
import com.github.tomokinakamaru.protocool.analysis.BuildSignature;
import com.github.tomokinakamaru.protocool.analysis.FindConflict;
import com.github.tomokinakamaru.protocool.analysis.FormatSkeleton;
import com.github.tomokinakamaru.protocool.analysis.GenerateApiSkeleton;
import com.github.tomokinakamaru.protocool.analysis.GenerateBaseFile;
import com.github.tomokinakamaru.protocool.analysis.GenerateClassAstSkeleton;
import com.github.tomokinakamaru.protocool.analysis.GenerateMethodAstSkeleton;
import com.github.tomokinakamaru.protocool.analysis.PropagateParameter;
import com.github.tomokinakamaru.protocool.analysis.RemoveFinalStates;
import com.github.tomokinakamaru.protocool.analysis.ResolveReference;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationLexer;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Protocool {

  public final List<Analysis> processes = new ArrayList<>(defaultProcesses());

  public SpecificationContext run(CharStream stream) {
    SpecificationContext ctx = parse(stream);
    processes.forEach(p -> p.run(ctx));
    return ctx;
  }

  public static SpecificationContext parse(CharStream stream) {
    SpecificationLexer lexer = new SpecificationLexer(stream);
    SpecificationParser parser = new SpecificationParser(new CommonTokenStream(lexer));
    return parser.specification();
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
