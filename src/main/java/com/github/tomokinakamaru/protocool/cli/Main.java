package com.github.tomokinakamaru.protocool.cli;

import com.github.tomokinakamaru.protocool.Protocool;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import picocli.CommandLine;

@CommandLine.Command(
    name = "protocool",
    description = "Fluent API generator",
    versionProvider = VersionProvider.class)
public final class Main implements Runnable {

  @CommandLine.Parameters(index = "0", description = "Input file")
  private Path inputPath = null;

  @CommandLine.Option(
      names = {"-o", "--output-dir"},
      description = "Output directory")
  private Path outputDirectory = Paths.get("");

  @SuppressWarnings("unused")
  @CommandLine.Option(
      names = {"-h", "--help"},
      usageHelp = true,
      description = "Display this help message")
  private boolean usageHelpRequested = false;

  @SuppressWarnings("unused")
  @CommandLine.Option(
      names = {"-v", "--version"},
      versionHelp = true,
      description = "Display version info")
  private boolean versionInfoRequested = false;

  private Main() {}

  public static void main(String[] args) {
    System.exit(new CommandLine(new Main()).execute(args));
  }

  @Override
  public void run() {
    new Protocool().run(getCharStream());
  }

  private CharStream getCharStream() {
    try {
      return CharStreams.fromStream(getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private InputStream getInputStream() {
    try {
      return inputPath == null ? System.in : new FileInputStream(inputPath.toFile());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
