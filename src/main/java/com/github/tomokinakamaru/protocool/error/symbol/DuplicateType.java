package com.github.tomokinakamaru.protocool.error.symbol;

import com.github.tomokinakamaru.protocool.error.Error;
import java.util.stream.Stream;

public class DuplicateType extends Error {

  public DuplicateType(Stream name) {
    super("Duplicate type: %s", name);
  }
}
