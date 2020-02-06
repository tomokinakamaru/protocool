package com.github.tomokinakamaru.protocool;

import com.github.javaparser.ast.CompilationUnit;
import com.github.tomokinakamaru.protocool.error.DuplicateType;
import com.github.tomokinakamaru.protocool.error.ParseError;
import com.github.tomokinakamaru.protocool.error.ReturnTypeConflict;
import com.github.tomokinakamaru.protocool.error.SignatureConflict;
import com.github.tomokinakamaru.protocool.error.UndefinedType;
import com.github.tomokinakamaru.utility.picocli.AbstractEntryPoint;
import com.github.tomokinakamaru.utility.picocli.ExitCode;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import picocli.CommandLine;

@CommandLine.Command(name = "protocool", description = "Fluent API generator")
public class EntryPoint extends AbstractEntryPoint {

  @CommandLine.Parameters(index = "0", description = "Input file", arity = "0..1")
  private File inputFile = null;

  @CommandLine.Option(
      names = {"-o", "--output-dir"},
      description = "Output directory")
  private Path outputDirectory = Paths.get("");

  private static final Formatter formatter = new Formatter();

  static {
    ExitCode.set(ParseError.class, 101);
    ExitCode.set(DuplicateType.class, 102);
    ExitCode.set(UndefinedType.class, 103);
    ExitCode.set(ReturnTypeConflict.class, 104);
    ExitCode.set(SignatureConflict.class, 105);
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
    InputStream inputStream = inputFile == null ? System.in : new FileInputStream(inputFile);
    CharStream charStream = CharStreams.fromStream(inputStream);
    Collection<CompilationUnit> units = new Protocool().run(charStream);
    for (CompilationUnit u : units) {
      save(u);
    }
  }

  private void save(CompilationUnit unit) throws IOException, FormatterException {
    Path path = getPath(unit);
    Files.createDirectories(path.getParent());
    Files.write(path, formatter.formatSource(unit.toString()).getBytes());
  }

  private Path getPath(CompilationUnit unit) {
    return outputDirectory
        .resolve(getPackage(unit).replace(".", File.separator))
        .resolve(unit.getType(0).getName().asString() + ".java");
  }

  private String getPackage(CompilationUnit unit) {
    return unit.getPackageDeclaration().map(d -> d.getName().asString()).orElse("");
  }
}
