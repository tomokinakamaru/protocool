package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.analyzer.Analyzer;
import com.github.tomokinakamaru.protocool.analyzer.reference.ReferenceResolver;
import com.github.tomokinakamaru.protocool.analyzer.scope.ChainScopeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.scope.ClassScopeAnalyzer;
import com.github.tomokinakamaru.protocool.analyzer.symboltable.ClassTableBuilder;
import com.github.tomokinakamaru.protocool.analyzer.symboltable.ImportTableBuilder;
import com.github.tomokinakamaru.protocool.analyzer.symboltable.ParameterTableBuilder;
import com.github.tomokinakamaru.protocool.parser.Lexer;
import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Protocool {

  public final List<Analyzer> analyzers = defaultAnalyzers();

  public Context run(CharStream charStream) {
    Context context = new Context();
    context.charStream = charStream;
    context.fileContext = new Parser(new CommonTokenStream(new Lexer(charStream))).file();
    analyzers.forEach(a -> a.context = context);
    analyzers.forEach(Analyzer::run);
    return context;
  }

  private static List<Analyzer> defaultAnalyzers() {
    return Arrays.asList(
        new ClassScopeAnalyzer(),
        new ChainScopeAnalyzer(),
        new ClassTableBuilder(),
        new ImportTableBuilder(),
        new ParameterTableBuilder(),
        new ReferenceResolver());
  }
}
