package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool._keep.reference.ReferenceResolver;
import com.github.tomokinakamaru.protocool._keep.scope.ChainScopeAnalyzer;
import com.github.tomokinakamaru.protocool._keep.scope.ClassScopeAnalyzer;
import com.github.tomokinakamaru.protocool._keep.symboltable.ClassTableBuilder;
import com.github.tomokinakamaru.protocool._keep.symboltable.ImportTableBuilder;
import com.github.tomokinakamaru.protocool._keep.symboltable.ParameterTableBuilder;
import com.github.tomokinakamaru.protocool.analyzer.Analyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.SyntaxAnalyzer;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;

public class Protocool {

  public final List<Analyzer> analyzers = defaultAnalyzers();

  public void run(CharStream charStream) {
    CompileContext context = new CompileContext();
    context.charStream = charStream;
    analyzers.forEach(a -> a.context = context);
    analyzers.forEach(Analyzer::run);
  }

  private static List<Analyzer> defaultAnalyzers() {
    return Arrays.asList(
        new SyntaxAnalyzer(),
        new ClassScopeAnalyzer(),
        new ChainScopeAnalyzer(),
        new ClassTableBuilder(),
        new ImportTableBuilder(),
        new ParameterTableBuilder(),
        new ReferenceResolver());
  }
}
