package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractStreamVisitor;
import java.util.stream.Stream;

public abstract class StreamVisitor<T> extends AbstractStreamVisitor<T>
    implements DefaultVisitor<Stream<T>> {}
