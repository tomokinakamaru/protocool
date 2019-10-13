import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomokinakamaru.protocool.parser.Lexer;
import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

abstract class TestParse {

  abstract Consumer<Parser> consumer();

  void testSuccess(String string) {
    Lexer lexer = new Lexer(CharStreams.fromString(string));
    Parser parser = new Parser(new CommonTokenStream(lexer));
    consumer().accept(parser);
  }

  void testFail(String string) {
    assertThrows(RuntimeException.class, () -> testSuccess(string));
  }
}
