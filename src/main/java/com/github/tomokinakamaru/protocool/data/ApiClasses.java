package com.github.tomokinakamaru.protocool.data;

import com.github.javaparser.ast.CompilationUnit;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import java.util.IdentityHashMap;

public class ApiClasses extends IdentityHashMap<State, CompilationUnit> {}
