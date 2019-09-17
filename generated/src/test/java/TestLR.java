import static lr.Begin.begin;

import lr.OK;
import org.junit.jupiter.api.Test;

public class TestLR {

  @Test
  void main() {
    OK ok1 = begin().begin().end().end();
    OK ok2 = begin().begin().end();
    OK ok3 = begin().begin();
  }
}
