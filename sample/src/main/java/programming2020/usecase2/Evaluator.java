package programming2020.usecase2;

import java.util.List;

public class Evaluator {

  public static <X> X end(List<AbstractMethodNode> nodes, Nested$X<X> node) {
    nodes.remove(nodes.size() - 1);
    nodes.remove(nodes.size() - 1);
    if (nodes.size() == 0) {
      EndOfDoc endOfDoc = new EndOfDoc();
      endOfDoc.methodNodes$ = nodes;
      return (X) endOfDoc;
    } else {
      Nested<X, ?> nested = new Nested<>();
      nested.methodNodes$ = nodes;
      return (X) nested;
    }
  }

  public static String asTeXStr(EndOfDoc$String node) {
    return "";
  }
}
