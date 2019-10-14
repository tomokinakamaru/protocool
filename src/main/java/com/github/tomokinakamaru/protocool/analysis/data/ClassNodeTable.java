package com.github.tomokinakamaru.protocool.analysis.data;

import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import java.util.LinkedHashMap;
import java.util.Set;

public class ClassNodeTable extends LinkedHashMap<String, Set<ChainContext>> {}
