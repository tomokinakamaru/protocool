package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;
import com.github.tomokinakamaru.protocool.data.reference.Entity;
import com.github.tomokinakamaru.protocool.data.reference.Table;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class Context {

  public CharStream charStream;

  public SpecificationContext specificationContext;

  public ParseTreeProperty<Table> tables = new ParseTreeProperty<>();

  public ParseTreeProperty<Entity> entities = new ParseTreeProperty<>();
}
