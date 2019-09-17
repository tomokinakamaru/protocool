package com.github.tomokinakamaru.protocool.skeleton;

import static com.github.tomokinakamaru.protocool.skeleton.Utility.buildParameters;

import com.github.tomokinakamaru.protocool.data.MethodNodeNames;
import com.github.tomokinakamaru.protocool.data.MethodNodeTable;
import com.github.tomokinakamaru.protocool.data.Parameters;
import com.github.tomokinakamaru.protocool.parser.SpecificationParser.ChainContext;
import java.util.Set;

public class ClassAstSkeletonBuilder extends FileBuilder {

  private final Set<ChainContext> contexts;

  public ClassAstSkeletonBuilder(Set<ChainContext> contexts) {
    this.contexts = contexts;
  }

  @Override
  public void build() {
    buildPath();
    buildHeader();
    buildClassHead();
    append("{");
    buildClassBody();
    append("}");
  }

  private void buildPath() {
    String fileName = getContext().nodeName + ".java";
    setPath(getContext().ownerClazz.ownerSpecification.packagePath.resolve(fileName));
  }

  private void buildHeader() {
    String pkg = getContext().ownerClazz.ownerSpecification.packageName;
    if (!pkg.isEmpty()) {
      append("package", pkg, ";");
    }
  }

  private void buildClassHead() {
    append("public", "final", "class", getContext().nodeName);

    Parameters parameters = new Parameters();
    for (ChainContext c : contexts) {
      parameters.addAll(c.parameters);
    }
    append(buildParameters(parameters, true));
  }

  private void buildClassBody() {
    MethodNodeNames names = new MethodNodeNames();
    for (ChainContext c : contexts) {
      for (String key : c.methodNodeNames.keySet()) {
        if (names.containsKey(key)) {
          if (c.methodNodeNames.get(key)) {
            names.put(key, true);
          }
        } else {
          names.put(key, c.methodNodeNames.get(key));
        }
      }
    }

    MethodNodeTable table = getContext().ownerClazz.ownerSpecification.methodNodeTable;
    for (String name : names.keySet()) {
      append("public");
      if (names.get(name)) {
        append("final");
        append("java.util.List<");
      }
      append(table.get(name).nodeName);
      append(buildParameters(table.get(name).parameters, false));
      if (names.get(name)) {
        append(">");
      }
      append(lowerFirstChar(table.get(name).nodeName));
      if (names.get(name)) {
        append("=", "new", "java.util.ArrayList<>()");
      }
      append(";");
    }
  }

  private ChainContext getContext() {
    return contexts.iterator().next();
  }

  private String lowerFirstChar(String s) {
    return s.substring(0, 1).toLowerCase() + s.substring(1);
  }
}
