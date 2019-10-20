package com.github.tomokinakamaru.protocool.data;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.tomokinakamaru.protocool.data.automaton.State;
import java.util.IdentityHashMap;

public class ApiClasses extends IdentityHashMap<State, ClassOrInterfaceDeclaration> {}
