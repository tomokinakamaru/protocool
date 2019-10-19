import com.github.tomokinakamaru.protocool.EntryPoint;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

final class IntegrationTest {

  private static final Path resources = Paths.get("src", "test", "resources");

  private static final Path outputDir = Paths.get("build", "protocool");

  @ParameterizedTest
  @MethodSource("findGrammarFiles")
  void test(Path path) {
    assert EntryPoint.execute(path.toString(), "-o", outputDir.toString()) == 0;
  }

  private static Stream<Path> findGrammarFiles() throws IOException {
    return Files.list(resources).filter(path -> path.toString().endsWith(".grammar"));
  }

  @BeforeAll
  private static void clean() throws IOException {
    Files.walk(outputDir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
  }
}
