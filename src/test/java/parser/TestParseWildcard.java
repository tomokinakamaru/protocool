package parser;

import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseWildcard extends TestParse {

  @Override
  Consumer<Parser> rule() {
    return Parser::wildcard;
  }

  @Override
  @ParameterizedTest
  @ValueSource(strings = {"?", "? extends A", "? super A"})
  void test(String s) {
    super.test(s);
  }

  @Override
  @ParameterizedTest
  @ValueSource(strings = {"? extends", "? super"})
  void testFail(String s) {
    super.testFail(s);
  }
}
