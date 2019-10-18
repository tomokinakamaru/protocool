package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractListener;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationListener;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.BodyContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ElementContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.FactorContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.FileContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.HeadContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ImportContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.InterfacesContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.NameContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.PackageContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.QualifiedNameContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.SuperClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.TermContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationParser.WildcardContext;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Listener extends AbstractListener implements SpecificationListener {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return FileContext.class;
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
  public void enterHead(HeadContext ctx) {}

  @Override
  public void exitHead(HeadContext ctx) {}

  @Override
  public void enterBody(BodyContext ctx) {}

  @Override
  public void exitBody(BodyContext ctx) {}

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
}
