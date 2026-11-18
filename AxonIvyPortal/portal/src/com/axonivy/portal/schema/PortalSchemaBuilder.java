package com.axonivy.portal.schema;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.axonivy.portal.dto.jsondefinition.PortalCustomStatistic;
import com.axonivy.portal.dto.jsondefinition.PortalDashboard;
import com.axonivy.portal.dto.jsondefinition.PortalDashboardTemplate;
import com.axonivy.portal.schema.generator.PortalSchemaGenerator;

public class PortalSchemaBuilder {
  private record SchemaDefinition(String fileName, Class<?> rootType) {}

  private static final List<SchemaDefinition> SCHEMA_FILE_LIST = List.of(
      new SchemaDefinition("portal-dashboard-schema.json", PortalDashboard.class),
      new SchemaDefinition("portal-custom-statistic-schema.json", PortalCustomStatistic.class),
      new SchemaDefinition("portal-dashboard-template-schema.json", PortalDashboardTemplate.class));

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
    for (SchemaDefinition def : SCHEMA_FILE_LIST) {
      writeSchema(generator, def, versionDir);
    }
  }

  private static void writeSchema(PortalSchemaGenerator generator, SchemaDefinition definition, Path outputDir) throws IOException {
    Path outputFile = outputDir.resolve(definition.fileName());
    var schema = generator.generateSchema(definition.rootType());
    Files.writeString(
        outputFile,
        schema.toPrettyString() + System.lineSeparator(),
        StandardCharsets.UTF_8);

    System.out.println("Generated schema: " + outputFile.toAbsolutePath());
  }
}
