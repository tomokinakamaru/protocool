import com.github.tomokinakamaru.protocool.cli.Main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

final class IntegrationTest {

  private static final Path resources = Paths.get("src", "test", "resources");

  @ParameterizedTest
  @MethodSource("findGrammarFiles")
  void test(Path path) {
    assert Main.execute(path.toString()) == 0;
  }

  private static Stream<Path> findGrammarFiles() throws IOException {
    return Files.list(resources).filter(path -> path.toString().endsWith(".grammar"));
  }
}
