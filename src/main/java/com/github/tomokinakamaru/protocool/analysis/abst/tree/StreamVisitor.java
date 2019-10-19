package com.github.tomokinakamaru.protocool.analysis.abst.tree;

import java.util.stream.Stream;

public abstract class StreamVisitor<T> extends Visitor<Stream<T>> {

  @Override
  public Stream<T> defaultResult() {
    return Stream.empty();
  }

  @Override
  public Stream<T> aggregateResult(Stream<T> aggregate, Stream<T> nextResult) {
    return Stream.concat(aggregate, nextResult);
  }
}
