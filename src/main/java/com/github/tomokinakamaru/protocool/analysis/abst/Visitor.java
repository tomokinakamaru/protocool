package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractVisitor;
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
import com.github.tomokinakamaru.protocool.analysis.antlr.SpecificationVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Visitor<T> extends AbstractVisitor<T> implements SpecificationVisitor<T> {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return FileContext.class;
  }

  @Override
  public T visitFile(FileContext ctx) {
    return null;
  }

  @Override
  public T visitPackage(PackageContext ctx) {
    return null;
  }

  @Override
  public T visitImport(ImportContext ctx) {
    return null;
  }

  @Override
  public T visitClass(ClassContext ctx) {
    return null;
  }

  @Override
  public T visitHead(HeadContext ctx) {
    return null;
  }

  @Override
  public T visitBody(BodyContext ctx) {
    return null;
  }

  @Override
  public T visitSuperClass(SuperClassContext ctx) {
    return null;
  }

  @Override
  public T visitInterfaces(InterfacesContext ctx) {
    return null;
  }

  @Override
  public T visitParameter(ParameterContext ctx) {
    return null;
  }

  @Override
  public T visitChain(ChainContext ctx) {
    return null;
  }

  @Override
  public T visitExpression(ExpressionContext ctx) {
    return null;
  }

  @Override
  public T visitTerm(TermContext ctx) {
    return null;
  }

  @Override
  public T visitFactor(FactorContext ctx) {
    return null;
  }

  @Override
  public T visitElement(ElementContext ctx) {
    return null;
  }

  @Override
  public T visitMethod(MethodContext ctx) {
    return null;
  }

  @Override
  public T visitArgument(ArgumentContext ctx) {
    return null;
  }

  @Override
  public T visitReference(ReferenceContext ctx) {
    return null;
  }

  @Override
  public T visitWildcard(WildcardContext ctx) {
    return null;
  }

  @Override
  public T visitQualifiedName(QualifiedNameContext ctx) {
    return null;
  }

  @Override
  public T visitName(NameContext ctx) {
    return null;
  }
}
