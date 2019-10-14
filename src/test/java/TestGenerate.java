import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomokinakamaru.protocool.Protocool;
import com.github.tomokinakamaru.protocool.exception.BadSpecification;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.antlr.v4.runtime.CharStreams;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

final class TestGenerate {

  private static final Path rootProject = Paths.get("").toAbsolutePath().getParent();

  private static final Path outputDirectory =
      Paths.get(rootProject.toString(), "generated", "src", "main", "gen");

  private static final Path testResources = Paths.get("src", "test", "resources");

  private static final String specSuffix = ".spec";

  private static final String badSpecSuffix = ".badspec";

  @ParameterizedTest
  @MethodSource("findSpecFiles")
  void testGenerateSuccess(Path path) throws IOException {
    testGenerate(path);
  }

  @ParameterizedTest
  @MethodSource("findBadSpecFiles")
  void testGenerateFail(Path path) {
    assertThrows(BadSpecification.class, () -> testGenerate(path));
  }

  private void testGenerate(Path path) throws IOException {
    new Protocool().run(CharStreams.fromPath(path)).skeletons.save(outputDirectory);
  }

  private static Stream<Path> findSpecFiles() throws IOException {
    return findFiles(specSuffix);
  }

  private static Stream<Path> findBadSpecFiles() throws IOException {
    return findFiles(badSpecSuffix);
  }

  private static Stream<Path> findFiles(String suffix) throws IOException {
    return Files.list(testResources).filter(path -> path.toString().endsWith(suffix));
  }

  @BeforeAll
  private static void cleanOutputDirectory() {
    FileUtils.deleteQuietly(outputDirectory.toFile());
  }
}
