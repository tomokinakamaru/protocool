package com.github.tomokinakamaru.protocool.analysis.abst;

import com.github.tomokinakamaru.antlr4.utility.AbstractStreamVisitor;
import java.util.stream.Stream;

public abstract class Visitor<T> extends AbstractStreamVisitor<T>
    implements DefaultVisitor<Stream<T>> {}
