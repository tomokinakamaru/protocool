package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractStreamVisitor;
import com.github.tomokinakamaru.protocool.antlr.GrammarParser;
import com.github.tomokinakamaru.protocool.antlr.GrammarVisitor;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.ErrorNode;

public abstract class Visitor<T> extends AbstractStreamVisitor<T>
    implements GrammarVisitor<Stream<T>> {

  @Override
  public Stream<T> visitErrorNode(ErrorNode node) {
    return super.visitErrorNode(node);
  }

  @Override
  public Stream<T> visitFile(GrammarParser.FileContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitPackage(GrammarParser.PackageContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitImport(GrammarParser.ImportContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitClass(GrammarParser.ClassContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitHead(GrammarParser.HeadContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitBody(GrammarParser.BodyContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitSuperClass(GrammarParser.SuperClassContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitInterfaces(GrammarParser.InterfacesContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitParameter(GrammarParser.ParameterContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitChain(GrammarParser.ChainContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitExpression(GrammarParser.ExpressionContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitTerm(GrammarParser.TermContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitFactor(GrammarParser.FactorContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitElement(GrammarParser.ElementContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitMethod(GrammarParser.MethodContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitArgument(GrammarParser.ArgumentContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitReference(GrammarParser.ReferenceContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitWildcard(GrammarParser.WildcardContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitQualifiedName(GrammarParser.QualifiedNameContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Stream<T> visitName(GrammarParser.NameContext ctx) {
    return visitChildren(ctx);
  }
}
