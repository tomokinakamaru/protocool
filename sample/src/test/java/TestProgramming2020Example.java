import static programming2020.example.OurAPI.newMap;

import java.util.Map;
import org.junit.jupiter.api.Test;

final class TestProgramming2020Example {

  @Test
  void test1() {
    Map<String, Integer> map = newMap().build();
    assert map.size() == 0;
  }

  @Test
  void test2() {
    Map<String, Integer> map = newMap().put("b", 2).build();
    assert map.size() == 1;
  }

  @Test
  void test3() {
    Map<Integer, String> map = newMap().put(1, "a").put(2, "b").build();
    assert map.size() == 2;
  }
}
