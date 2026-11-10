package com.axonivy.portal.jsonschema;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.axonivy.portal.jsonschema.definition.PortalCustomStatistic;
import com.axonivy.portal.jsonschema.definition.PortalDashboard;
import com.axonivy.portal.jsonschema.definition.PortalDashboardTemplate;
import com.axonivy.portal.jsonschema.generator.PortalSchemaGenerator;

public class PortalSchemaBuilder {
  private record SchemaDefinition(String fileName, Class<?> rootType) {}

  private static final List<SchemaDefinition> SCHEMAS = List.of(
      new SchemaDefinition("portal-dashboard-schema.json", PortalDashboard.class),
      new SchemaDefinition("portal-statistic-schema.json", PortalCustomStatistic.class),
      new SchemaDefinition("portal-dashboard-template-schema.json", PortalDashboardTemplate.class));

  public static void main(String[] args) {
    if (args.length != 2 || args[0].isBlank() || args[1].isBlank()) {
      throw new IllegalArgumentException(
          "Usage: PortalSchemaBuilder <output-directory> <portal-version>");
    }
    Path outDir = Path.of(args[0]);
    String portalVersion = args[1];
    Path versionDir = outDir.resolve(portalVersion);
    try {
      Files.createDirectories(versionDir);
      var generator = new PortalSchemaGenerator();
      for (SchemaDefinition def : SCHEMAS) {
        writeSchema(generator, def, versionDir);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private static void writeSchema(PortalSchemaGenerator generator, SchemaDefinition definition, Path outputDir) throws IOException {
    Path outputFile = outputDir.resolve(definition.fileName());
    var schema = generator.generateSchema(definition.rootType());
    schema.withObject("/properties").putObject("$schema").put("type", "string");
    schema.put("$id", outputFile.toAbsolutePath().toString());
    Files.writeString(
        outputFile,
        schema.toPrettyString() + System.lineSeparator(),
        StandardCharsets.UTF_8);

    System.out.println("Generated schema: " + outputFile.toAbsolutePath());
  }
}
