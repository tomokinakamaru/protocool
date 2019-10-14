package com.github.tomokinakamaru.protocool.data.symboltable;

import java.util.LinkedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class SymbolTable<T extends ParserRuleContext> extends LinkedHashMap<String, T> {}
