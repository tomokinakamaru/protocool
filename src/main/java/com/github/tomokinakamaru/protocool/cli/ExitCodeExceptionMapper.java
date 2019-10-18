package com.github.tomokinakamaru.protocool.cli;

import com.github.tomokinakamaru.protocool.analyzer.error.ExitCode;
import picocli.CommandLine.IExitCodeExceptionMapper;

final class ExitCodeExceptionMapper implements IExitCodeExceptionMapper {

  @Override
  public int getExitCode(Throwable exception) {
    return ExitCode.get(exception.getClass());
  }
}
