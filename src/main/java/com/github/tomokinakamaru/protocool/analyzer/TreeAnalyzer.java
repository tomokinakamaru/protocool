package com.github.tomokinakamaru.protocool.analyzer;

import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarListener;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ElementContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.FactorContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.InterfacesContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.NameContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.PackageContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.QualifiedNameContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.SuperClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.TermContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.GrammarParser.WildcardContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public abstract class TreeAnalyzer extends Analyzer implements GrammarListener {

  @Override
  public final void run() {
    ParseTreeWalker.DEFAULT.walk(this, context.tree);
  }

  @Override
  public void enterFile(FileContext ctx) {}

  @Override
  public void exitFile(FileContext ctx) {}

  @Override
  public void enterPackage(PackageContext ctx) {}

  @Override
  public void exitPackage(PackageContext ctx) {}

  @Override
  public void enterImport(ImportContext ctx) {}

  @Override
  public void exitImport(ImportContext ctx) {}

  @Override
  public void enterClass(ClassContext ctx) {}

  @Override
  public void exitClass(ClassContext ctx) {}

  @Override
  public void enterSuperClass(SuperClassContext ctx) {}

  @Override
  public void exitSuperClass(SuperClassContext ctx) {}

  @Override
  public void enterInterfaces(InterfacesContext ctx) {}

  @Override
  public void exitInterfaces(InterfacesContext ctx) {}

  @Override
  public void enterParameter(ParameterContext ctx) {}

  @Override
  public void exitParameter(ParameterContext ctx) {}

  @Override
  public void enterChain(ChainContext ctx) {}

  @Override
  public void exitChain(ChainContext ctx) {}

  @Override
  public void enterExpression(ExpressionContext ctx) {}

  @Override
  public void exitExpression(ExpressionContext ctx) {}

  @Override
  public void enterTerm(TermContext ctx) {}

  @Override
  public void exitTerm(TermContext ctx) {}

  @Override
  public void enterFactor(FactorContext ctx) {}

  @Override
  public void exitFactor(FactorContext ctx) {}

  @Override
  public void enterElement(ElementContext ctx) {}

  @Override
  public void exitElement(ElementContext ctx) {}

  @Override
  public void enterMethod(MethodContext ctx) {}

  @Override
  public void exitMethod(MethodContext ctx) {}

  @Override
  public void enterArgument(ArgumentContext ctx) {}

  @Override
  public void exitArgument(ArgumentContext ctx) {}

  @Override
  public void enterReference(ReferenceContext ctx) {}

  @Override
  public void exitReference(ReferenceContext ctx) {}

  @Override
  public void enterWildcard(WildcardContext ctx) {}

  @Override
  public void exitWildcard(WildcardContext ctx) {}

  @Override
  public void enterQualifiedName(QualifiedNameContext ctx) {}

  @Override
  public void exitQualifiedName(QualifiedNameContext ctx) {}

  @Override
  public void enterName(NameContext ctx) {}

  @Override
  public void exitName(NameContext ctx) {}

  @Override
  public void visitTerminal(TerminalNode node) {}

  @Override
  public void visitErrorNode(ErrorNode node) {}

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {}

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {}
}
