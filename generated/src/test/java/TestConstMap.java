import constmap.ConstMap;
import constmap.ConstMapBuilder;
import org.junit.jupiter.api.Test;

final class TestConstMap {

  @Test
  void main() {
    ConstMap<Integer, Integer> m1 = ConstMapBuilder.add(1, 1).add(2, 3).build();
    m1.get(1);
    ConstMap<Integer, Integer> m2 = ConstMapBuilder.build();
    m2.get(1);
  }
}
