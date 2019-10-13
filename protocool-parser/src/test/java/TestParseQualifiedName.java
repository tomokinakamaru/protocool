import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseQualifiedName extends TestParse {

  @Override
  Consumer<Parser> consumer() {
    return Parser::qualifiedName;
  }

  @ParameterizedTest
  @ValueSource(strings = {"name", "name.name", "name.name.name"})
  void testSuccess(String string) {
    super.testSuccess(string);
  }

  @ParameterizedTest
  @ValueSource(strings = {"name.", ".name"})
  void testParseNameFail(String string) {
    super.testFail(string);
  }
}
