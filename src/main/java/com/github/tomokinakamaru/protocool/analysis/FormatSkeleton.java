package com.github.tomokinakamaru.protocool.analysis;

import com.github.tomokinakamaru.protocool.analysis.abst.Analysis;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.SpecificationContext;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class FormatSkeleton extends Analysis {

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
