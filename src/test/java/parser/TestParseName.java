package parser;

import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseName extends TestParse {

  @Override
  Consumer<Parser> rule() {
    return Parser::name;
  }

  @Override
  @ParameterizedTest
  @ValueSource(strings = {"name", "name0", "name_"})
  void test(String s) {
    super.test(s);
  }

  @Override
  @ParameterizedTest
  @ValueSource(strings = {"0name", "name$"})
  void testFail(String s) {
    super.testFail(s);
  }
}
