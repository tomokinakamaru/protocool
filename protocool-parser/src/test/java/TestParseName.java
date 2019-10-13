import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseName extends TestParse {

  @Override
  Consumer<Parser> consumer() {
    return Parser::name;
  }

  @ParameterizedTest
  @ValueSource(strings = {"name", "name_name", "name0", "Name"})
  void test(String string) {
    super.test(string);
  }

  @ParameterizedTest
  @ValueSource(strings = {"name$name", "0name"})
  void testParseNameFail(String string) {
    super.testFail(string);
  }
}
