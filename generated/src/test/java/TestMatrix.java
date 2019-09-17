import static matrix.API.randFlt;

import matrix.Size;
import org.junit.jupiter.api.Test;

final class TestMatrix {

  private static final class Dim128 extends Size {}

  private static final class Dim256 extends Size {}

  private static final Dim128 dim128 = new Dim128();

  private static final Dim256 dim256 = new Dim256();

  @Test
  void main() {
    randFlt().row(dim256).col(dim128).mult(randFlt().row(dim128).col(dim128)).toArray();
  }
}
