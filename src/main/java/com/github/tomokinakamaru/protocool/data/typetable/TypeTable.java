package com.github.tomokinakamaru.protocool.data.typetable;

import com.github.tomokinakamaru.antlr4.utility.AbstractSymbolTable;
import com.github.tomokinakamaru.protocool.Error;
import java.util.function.Supplier;
import org.antlr.v4.runtime.ParserRuleContext;

public class TypeTable extends AbstractSymbolTable<TypeTable, ParserRuleContext> {

  @Override
  protected Supplier<TypeTable> newSymbolTable() {
    return TypeTable::new;
  }

  @Override
  protected ParserRuleContext resolveConflict(String k, ParserRuleContext o, ParserRuleContext n) {
    throw new Error.DuplicateType(k);
  }

  @Override
  protected ParserRuleContext defaultEntity(String key) {
    throw new Error.UndefinedType(key);
  }
}
