package com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder;

class CodeBuilder {

  private final StringBuilder stringBuilder = new StringBuilder();

  final void append(String... strings) {
    for (String s : strings) {
      stringBuilder.append(" ").append(s).append(" ");
    }
  }

  @Override
  public String toString() {
    return stringBuilder.toString();
  }
}
