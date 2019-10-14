package com.github.tomokinakamaru.protocool.analysis.skeleton.codebuilder;

import com.github.tomokinakamaru.protocool.data.Parameters;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ParameterContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ReferenceContext;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

final class Utility {

  private Utility() {}

  static String buildParameters(Parameters parameters, boolean bound) {
    if (parameters.isEmpty()) {
      return "";
    }

    CodeBuilder builder = new CodeBuilder();
    builder.append("<");
    for (ParameterContext p : parameters) {
      builder.append(p.name().getText());
      if (bound) {
        ReferenceContext c = p.reference();
        if (c != null) {
          builder.append("extends");
          builder.append(getText(c));
        }
      }
      if (isNotLastItem(new ArrayList<>(parameters), p)) {
        builder.append(",");
      }
    }
    builder.append(">");
    return builder.toString();
  }

  static <T> boolean isNotLastItem(List<T> list, T item) {
    return list.indexOf(item) != list.size() - 1;
  }

  static String getText(ParserRuleContext ctx) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < ctx.getChildCount(); i++) {
      ParseTree t = ctx.getChild(i);
      if (t instanceof ParserRuleContext) {
        builder.append(getText((ParserRuleContext) t)).append(" ");
      } else {
        builder.append(t.getText()).append(" ");
      }
    }
    return builder.toString();
  }
}
