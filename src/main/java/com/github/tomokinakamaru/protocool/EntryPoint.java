package com.github.tomokinakamaru.protocool;

import com.github.tomokinakamaru.picocli.utility.AbstractEntryPoint;
import com.github.tomokinakamaru.picocli.utility.ExitCode;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.antlr.v4.runtime.CharStreams;
import picocli.CommandLine;

@CommandLine.Command(name = "protocool", description = "Fluent API generator")
public final class EntryPoint extends AbstractEntryPoint {

  @CommandLine.Parameters(index = "0", description = "Input file")
  private Path inputPath = null;

  @CommandLine.Option(
      names = {"-o", "--output-dir"},
      description = "Output directory")
  private Path outputDirectory = Paths.get("");

  static {
    ExitCode.set(Error.ParseError.class, 101);
    ExitCode.set(Error.DuplicateType.class, 102);
    ExitCode.set(Error.UndefinedType.class, 103);
  }

  private EntryPoint() {}

  public static void main(String[] args) {
    main(new EntryPoint(), args);
  }

  public static int execute(String... args) {
    return execute(new EntryPoint(), args);
  }

  @Override
  protected void main() throws Exception {
    new Protocool().run(CharStreams.fromStream(getInputStream()));
  }

  private InputStream getInputStream() throws FileNotFoundException {
    return inputPath == null ? System.in : new FileInputStream(inputPath.toFile());
  }
}
