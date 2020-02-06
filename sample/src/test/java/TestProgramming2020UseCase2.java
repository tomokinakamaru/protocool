import static programming2020.usecase2.API.begin;

import org.junit.jupiter.api.Test;

final class TestProgramming2020UseCase2 {

  @Test
  void test1() {
    begin(1).begin(2).begin(3).end(2).end(1).end(0).asTeXStr();
  }

  @Test
  void test2() {
    begin(1).begin(2).end(2).end(0).asTeXStr();
  }

  @Test
  void test3() {
    begin(1).end(0).asTeXStr();
  }
}
