package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.protocool.data.automaton.Automaton;
import com.github.tomokinakamaru.protocool.data.symboltable.ClassTable;
import com.github.tomokinakamaru.protocool.data.symboltable.ImportTable;
import com.github.tomokinakamaru.protocool.data.symboltable.ParameterTable;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.Class_Context;
import com.github.tomokinakamaru.protocool.parser.antlr.GrammarParser.FileContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class Context {

  public CharStream charStream;

  public FileContext fileContext;

  public ParseTreeProperty<Class_Context> classContexts = new ParseTreeProperty<>();

  public ParseTreeProperty<ChainContext> chainContexts = new ParseTreeProperty<>();

  public ParseTreeProperty<ClassTable> classTables = new ParseTreeProperty<>();

  public ParseTreeProperty<ImportTable> importTables = new ParseTreeProperty<>();

  public ParseTreeProperty<ParameterTable> parameterTables = new ParseTreeProperty<>();

  public ParseTreeProperty<ParserRuleContext> references = new ParseTreeProperty<>();

  public ParseTreeProperty<Automaton> automata = new ParseTreeProperty<>();
}
