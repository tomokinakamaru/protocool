import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseWildcard extends TestParse {

  @Override
  Consumer<Parser> consumer() {
    return Parser::wildcard;
  }

  @ParameterizedTest
  @ValueSource(strings = {"?", "? extends T", "? super T"})
  void testSuccess(String string) {
    super.testSuccess(string);
  }
}
