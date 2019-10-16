package com.github.tomokinakamaru.protocool.error;

import com.github.tomokinakamaru.protocool.error.symbol.DuplicateType;
import com.github.tomokinakamaru.protocool.error.symbol.UndefinedType;
import com.github.tomokinakamaru.protocool.error.syntax.LexicalError;
import com.github.tomokinakamaru.protocool.error.syntax.SyntacticalError;
import java.util.HashMap;
import java.util.Map;

public class ExitCode {

  public static final int SUCCESS = 0;

  public static final int UNKNOWN = 1;

  public static final int INVALID_USAGE = 2;

  private static Map<Class<? extends Error>, Integer> map = new HashMap<>();

  static {
    map.put(LexicalError.class, 101);
    map.put(SyntacticalError.class, 102);
    map.put(DuplicateType.class, 201);
    map.put(UndefinedType.class, 202);
  }

  public static int get(Class<? extends Throwable> c) {
    return map.getOrDefault(c, UNKNOWN);
  }
}
