package com.github.tomokinakamaru.protocool._keep.symbol;

import com.github.tomokinakamaru.protocool.error.Error;
import java.util.stream.Stream;

public class DuplicateType extends Error {

  public DuplicateType(Stream name) {
    super("Duplicate type: %s", name);
  }
}
