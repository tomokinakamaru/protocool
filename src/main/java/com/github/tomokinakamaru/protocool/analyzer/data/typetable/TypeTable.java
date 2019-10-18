package com.github.tomokinakamaru.protocool.analyzer.data.typetable;

import com.github.tomokinakamaru.antlr4.utility.AbstractSymbolTable;
import com.github.tomokinakamaru.protocool.analyzer.error.DuplicateType;
import com.github.tomokinakamaru.protocool.analyzer.error.UndefinedType;
import java.util.function.Supplier;
import org.antlr.v4.runtime.ParserRuleContext;

public class TypeTable extends AbstractSymbolTable<TypeTable, ParserRuleContext> {

  @Override
  protected Supplier<TypeTable> newSymbolTable() {
    return TypeTable::new;
  }

  @Override
  protected ParserRuleContext resolveConflict(String k, ParserRuleContext o, ParserRuleContext n) {
    throw new DuplicateType(k);
  }

  @Override
  protected ParserRuleContext defaultEntity(String key) {
    throw new UndefinedType(key);
  }
}
