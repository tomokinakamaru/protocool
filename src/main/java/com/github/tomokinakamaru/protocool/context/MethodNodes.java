package com.github.tomokinakamaru.protocool.context;

import com.github.javaparser.ast.CompilationUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodNodes extends HashMap<String, HashMap<String, CompilationUnit>> {

  public Collection<CompilationUnit> getCompilationUnits() {
    return values()
        .stream()
        .map(map -> map.values().stream())
        .flatMap(Function.identity())
        .collect(Collectors.toSet());
  }
}
