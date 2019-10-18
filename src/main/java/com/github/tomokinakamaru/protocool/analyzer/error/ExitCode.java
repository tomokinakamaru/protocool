package com.github.tomokinakamaru.protocool.analyzer.error;

import java.util.HashMap;
import java.util.Map;

public class ExitCode {

  public static final int SUCCESS = 0;

  public static final int UNKNOWN = 1;

  public static final int INVALID_USAGE = 2;

  private static Map<Class<? extends Error>, Integer> map = new HashMap<>();

  static {
    map.put(ParseError.class, 101);
  }

  public static int get(Class<? extends Throwable> c) {
    return map.getOrDefault(c, UNKNOWN);
  }
}
