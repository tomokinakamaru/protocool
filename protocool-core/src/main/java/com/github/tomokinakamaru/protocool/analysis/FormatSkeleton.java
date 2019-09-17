package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.Analysis;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.SpecificationContext;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public final class FormatSkeleton extends Analysis {

  private static final Formatter formatter = new Formatter();

  @Override
  public void enterSpecification(SpecificationContext ctx) {
    ctx.skeletons.forEach(skeleton -> skeleton.content = tryFormat(skeleton.content));
  }

  private static String tryFormat(String content) {
    try {
      return formatter.formatSource(content);
    } catch (FormatterException e) {
      return content;
    }
  }
}
