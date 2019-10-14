package com.github.tomokinakamaru.protocool.analysis.misc;

import com.github.tomokinakamaru.protocool.analysis.Analysis;
import com.github.tomokinakamaru.protocool.analysis.data.MethodNodeNames;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationBaseVisitor;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ChainContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ElementContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.ExpressionContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.FactorContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.MethodContext;
import com.github.tomokinakamaru.protocool.parser.antlr.SpecificationParser.TermContext;

public class BuildMethodNodeNames extends Analysis {

  private static final Visitor visitor = new Visitor();

  @Override
  public void enterChain(ChainContext ctx) {
    ctx.methodNodeNames.putAll(visitor.visitExpression(ctx.expression()));
  }

  private static final class Visitor extends SpecificationBaseVisitor<MethodNodeNames> {

    @Override
    public MethodNodeNames visitExpression(ExpressionContext ctx) {
      MethodNodeNames result = new MethodNodeNames();
      for (TermContext c : ctx.term()) {
        MethodNodeNames names = visitTerm(c);
        for (String key : names.keySet()) {
          if (result.containsKey(key)) {
            if (!result.get(key) && names.get(key)) {
              result.put(key, true);
            }
          } else {
            result.put(key, names.get(key));
          }
        }
      }
      return result;
    }

    @Override
    public MethodNodeNames visitTerm(TermContext ctx) {
      MethodNodeNames result = new MethodNodeNames();
      for (FactorContext c : ctx.factor()) {
        MethodNodeNames names = visitFactor(c);
        for (String key : names.keySet()) {
          if (result.containsKey(key)) {
            result.put(key, true);
          } else {
            result.put(key, names.get(key));
          }
        }
      }
      return result;
    }

    @Override
    public MethodNodeNames visitFactor(FactorContext ctx) {
      MethodNodeNames result = visitElement(ctx.element());
      if (ctx.REPEAT0() != null || ctx.REPEAT1() != null) {
        result.replaceAll((k, v) -> true);
        return result;
      } else {
        return result;
      }
    }

    @Override
    public MethodNodeNames visitElement(ElementContext ctx) {
      return ctx.method() == null ? visitExpression(ctx.expression()) : visitMethod(ctx.method());
    }

    @Override
    public MethodNodeNames visitMethod(MethodContext ctx) {
      MethodNodeNames result = new MethodNodeNames();
      result.put(ctx.nodeName, false);
      return result;
    }
  }
}
