package programming2020.example;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {

  public static <K, V> Map<K, V> map(OurAPI$java_util_Map node) {
    MapConstructor c = new MapConstructor();
    node.accept(c);
    return (Map<K, V>) c.map;
  }

  private static final class MapConstructor implements Visitor {

    private Map<Object, Object> map = new HashMap<>();

    @Override
    public <K, V> void visit(OurAPI$put<K, V> node) {
      map.put(node.key, node.value);
    }
  }
}
