package com.github.tomokinakamaru.protocool.data.reference;

import com.github.tomokinakamaru.protocool.error.reference.DuplicateType;
import com.github.tomokinakamaru.protocool.error.reference.UndefinedType;
import java.util.LinkedHashMap;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;

public class Table {

  private final Table parent;

  private final Map<String, Entity> map = new LinkedHashMap<>();

  public Table() {
    this.parent = null;
  }

  private Table(Table parent) {
    this.parent = parent;
  }

  public Table newChild() {
    return new Table(this);
  }

  public Entity get(String key) {
    if (map.containsKey(key)) {
      return map.get(key);
    }
    if (parent == null) {
      throw new DuplicateType(key);
    }
    return parent.get(key);
  }

  public void set(String key, ParserRuleContext context) {
    if (map.containsKey(key)) {
      throw new UndefinedType(key);
    }
    map.put(key, new Entity(context));
  }
}
