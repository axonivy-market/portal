package com.axonivy.portal.schema;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.axonivy.portal.enums.PortalSchema;
import com.axonivy.portal.schema.generator.PortalSchemaGenerator;

public class PortalSchemaBuilder {

  public static void main(String[] args) throws IOException {
    if (args.length != 2 || args[0].isBlank() || args[1].isBlank()) {
      throw new IllegalArgumentException(
          "Usage: PortalSchemaBuilder <output-directory> <portal-version>");
    }
    Path outDir = Path.of(args[0]);
    String portalVersion = args[1];
    Path versionDir = outDir.resolve(portalVersion);
    Files.createDirectories(versionDir);
    var generator = new PortalSchemaGenerator();
    for (PortalSchema def : PortalSchema.values()) {
      writeSchema(generator, def, versionDir);
    }
  }

  private static void writeSchema(PortalSchemaGenerator generator, PortalSchema definition, Path outputDir) throws IOException {
    Path outputFile = outputDir.resolve(definition.getFileName());
    var schema = generator.generateSchema(definition.getRootType());
    Files.writeString(
        outputFile,
        schema.toPrettyString() + System.lineSeparator(),
        StandardCharsets.UTF_8);

    System.out.println("Generated schema: " + outputFile.toAbsolutePath());
  }
}
