import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class TestParseReference extends TestParse {

  @Override
  Consumer<Parser> consumer() {
    return Parser::reference;
  }

  @ParameterizedTest
  @ValueSource(strings = {"T", "T<P>", "T<P,P>", "T[]"})
  void testSuccess(String string) {
    super.testSuccess(string);
  }
}
