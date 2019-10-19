package com.github.tomokinakamaru.protocool.analysis.abst.tree;

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

public interface DefaultVisitor<T> extends SpecificationVisitor<T> {

  default T visitFile(FileContext ctx) {
    return visitChildren(ctx);
  }

  default T visitPackage(PackageContext ctx) {
    return visitChildren(ctx);
  }

  default T visitImport(ImportContext ctx) {
    return visitChildren(ctx);
  }

  default T visitClass(ClassContext ctx) {
    return visitChildren(ctx);
  }

  default T visitHead(HeadContext ctx) {
    return visitChildren(ctx);
  }

  default T visitBody(BodyContext ctx) {
    return visitChildren(ctx);
  }

  default T visitSuperClass(SuperClassContext ctx) {
    return visitChildren(ctx);
  }

  default T visitInterfaces(InterfacesContext ctx) {
    return visitChildren(ctx);
  }

  default T visitParameter(ParameterContext ctx) {
    return visitChildren(ctx);
  }

  default T visitChain(ChainContext ctx) {
    return visitChildren(ctx);
  }

  default T visitExpression(ExpressionContext ctx) {
    return visitChildren(ctx);
  }

  default T visitTerm(TermContext ctx) {
    return visitChildren(ctx);
  }

  default T visitFactor(FactorContext ctx) {
    return visitChildren(ctx);
  }

  default T visitElement(ElementContext ctx) {
    return visitChildren(ctx);
  }

  default T visitMethod(MethodContext ctx) {
    return visitChildren(ctx);
  }

  default T visitArgument(ArgumentContext ctx) {
    return visitChildren(ctx);
  }

  default T visitReference(ReferenceContext ctx) {
    return visitChildren(ctx);
  }

  default T visitWildcard(WildcardContext ctx) {
    return visitChildren(ctx);
  }

  default T visitQualifiedName(QualifiedNameContext ctx) {
    return visitChildren(ctx);
  }

  default T visitName(NameContext ctx) {
    return visitChildren(ctx);
  }
}
