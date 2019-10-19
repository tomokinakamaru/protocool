package com.github.tomokinakamaru.protocool.analysis.abst.tree;

import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ArgumentContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.BodyContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ElementContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.FactorContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.FileContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.HeadContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ImportContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.InterfacesContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.MethodContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.NameContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.PackageContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ParameterContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.QualifiedNameContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.ReferenceContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.SuperClassContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.TermContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarParser.WildcardContext;
import com.github.tomokinakamaru.protocool.analysis.antlr.GrammarVisitor;

public interface DefaultVisitor<T> extends GrammarVisitor<T> {

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
