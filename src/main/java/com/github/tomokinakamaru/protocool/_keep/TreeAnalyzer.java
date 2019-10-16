package com.github.tomokinakamaru.protocool._keep;

import com.github.tomokinakamaru.protocool.analyzer.Analyzer;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationListener;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ElementContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.FactorContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.InterfacesContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.NameContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.PackageContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.QualifiedNameContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SuperClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.TermContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.WildcardContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public abstract class TreeAnalyzer extends Analyzer implements SpecificationListener {

  @Override
  public final void run() {
    ParseTreeWalker.DEFAULT.walk(this, context.specificationContext);
  }

  @Override
  public void enterSpecification(SpecificationContext ctx) {}

  @Override
  public void exitSpecification(SpecificationContext ctx) {}

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
