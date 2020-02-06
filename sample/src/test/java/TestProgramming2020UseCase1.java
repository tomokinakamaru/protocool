import static programming2020.usecase1.Matrix.randFlt;
import static programming2020.usecase1.Matrix.randInt;

import org.junit.jupiter.api.Test;
import programming2020.usecase1.FltMat;
import programming2020.usecase1.IntMat;
import programming2020.usecase1.Size;

final class TestProgramming2020UseCase1 {

  private static final class Size128 extends Size {}

  private static final class Size256 extends Size {}

  private static final Size128 size128 = new Size128();

  private static final Size256 size256 = new Size256();

  @Test
  void test1() {
    IntMat<Size128, Size256> m1 = randInt().row(size128).col(size256);
    IntMat<Size128, Size256> m2 = randInt().row(size128).col(size256);
    IntMat<Size128, Size256> m3 = m1.plus(m2);
  }

  @Test
  void test2() {
    IntMat<Size128, Size256> m1 = randInt().row(size128).col(size256);
    IntMat<Size256, Size128> m2 = randInt().row(size256).col(size128);
    IntMat<Size128, Size128> m3 = m1.mult(m2);
  }

  @Test
  void test3() {
    FltMat<Size128, Size256> m1 = randFlt().row(size128).col(size256);
    IntMat<Size256, Size128> m2 = randInt().row(size256).col(size128);
    FltMat<Size128, Size128> m3 = m1.mult(m2);
  }
}
