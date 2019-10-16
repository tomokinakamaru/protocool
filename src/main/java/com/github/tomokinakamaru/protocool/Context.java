package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool._keep.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool._keep.data.symboltable.ClassTable;
import com.github.tomokinakamaru.protocool._keep.data.symboltable.ImportTable;
import com.github.tomokinakamaru.protocool._keep.data.symboltable.ParameterTable;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.ClassContext;
import com.github.tomokinakamaru.protocool.analyzer.syntax.antlr.SpecificationParser.SpecificationContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class Context {

  public CharStream charStream;

  public SpecificationContext tree;

  public ParseTreeProperty<ClassContext> classScope = new ParseTreeProperty<>();

  public ParseTreeProperty<ChainContext> chainScope = new ParseTreeProperty<>();

  public ParseTreeProperty<ClassTable> classTables = new ParseTreeProperty<>();

  public ParseTreeProperty<ImportTable> importTables = new ParseTreeProperty<>();

  public ParseTreeProperty<ParameterTable> parameterTables = new ParseTreeProperty<>();

  public ParseTreeProperty<ParserRuleContext> references = new ParseTreeProperty<>();

  public ParseTreeProperty<Automaton> automata = new ParseTreeProperty<>();
}
