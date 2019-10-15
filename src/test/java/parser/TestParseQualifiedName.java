package parser;

import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseQualifiedName extends TestParse {

  @Override
  Consumer<Parser> rule() {
    return Parser::qualifiedName;
  }

  @Override
  @ParameterizedTest
  @ValueSource(strings = {"name", "name.name", "name.name.name"})
  void test(String s) {
    super.test(s);
  }

  @Override
  @ParameterizedTest
  @ValueSource(strings = {".name", "name.", ""})
  void testFail(String s) {
    super.testFail(s);
  }
}
