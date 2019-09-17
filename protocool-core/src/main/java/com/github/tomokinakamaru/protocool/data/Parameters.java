package com.github.tomokinakamaru.protocool.data;

import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ParameterContext;
import java.util.LinkedHashSet;
import java.util.List;

public class Parameters extends LinkedHashSet<ParameterContext> {

  public Parameters() {
    super();
  }

  public Parameters(List<ParameterContext> list) {
    super(list);
  }

  public Parameters copy() {
    Parameters ps = new Parameters();
    ps.addAll(this);
    return ps;
  }

  public Parameters union(Parameters parameters) {
    Parameters ps = copy();
    ps.addAll(parameters);
    return ps;
  }
}
