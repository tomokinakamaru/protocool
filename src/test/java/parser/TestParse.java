package parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomokinakamaru.protocool.parser.Lexer;
import com.github.tomokinakamaru.protocool.parser.Parser;
import java.util.function.Consumer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

abstract class TestParse {

  abstract Consumer<Parser> rule();

  void test(String s) {
    rule().accept(createParser(s));
  }

  void testFail(String s) {
    assertThrows(RuntimeException.class, () -> test(s));
  }

  private static Parser createParser(String s) {
    return new Parser(new CommonTokenStream(new Lexer(CharStreams.fromString(s))));
  }
}
