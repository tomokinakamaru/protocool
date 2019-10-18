package com.github.tomokinakamaru.protocool.analyzer.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractVisitor;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Visitor<T> extends AbstractVisitor<T> implements SpecificationVisitor<T> {

  @Override
  protected Class<? extends ParserRuleContext> getContextClass() {
    return SpecificationParser.SpecificationContext.class;
  }

  @Override
  public T visitSpecification(SpecificationParser.SpecificationContext ctx) {
    return null;
  }

  @Override
  public T visitPackage(SpecificationParser.PackageContext ctx) {
    return null;
  }

  @Override
  public T visitImport(SpecificationParser.ImportContext ctx) {
    return null;
  }

  @Override
  public T visitClass(SpecificationParser.ClassContext ctx) {
    return null;
  }

  @Override
  public T visitSuperClass(SpecificationParser.SuperClassContext ctx) {
    return null;
  }

  @Override
  public T visitInterfaces(SpecificationParser.InterfacesContext ctx) {
    return null;
  }

  @Override
  public T visitParameter(SpecificationParser.ParameterContext ctx) {
    return null;
  }

  @Override
  public T visitChain(SpecificationParser.ChainContext ctx) {
    return null;
  }

  @Override
  public T visitExpression(SpecificationParser.ExpressionContext ctx) {
    return null;
  }

  @Override
  public T visitTerm(SpecificationParser.TermContext ctx) {
    return null;
  }

  @Override
  public T visitFactor(SpecificationParser.FactorContext ctx) {
    return null;
  }

  @Override
  public T visitElement(SpecificationParser.ElementContext ctx) {
    return null;
  }

  @Override
  public T visitMethod(SpecificationParser.MethodContext ctx) {
    return null;
  }

  @Override
  public T visitArgument(SpecificationParser.ArgumentContext ctx) {
    return null;
  }

  @Override
  public T visitReference(SpecificationParser.ReferenceContext ctx) {
    return null;
  }

  @Override
  public T visitWildcard(SpecificationParser.WildcardContext ctx) {
    return null;
  }

  @Override
  public T visitQualifiedName(SpecificationParser.QualifiedNameContext ctx) {
    return null;
  }

  @Override
  public T visitName(SpecificationParser.NameContext ctx) {
    return null;
  }
}
