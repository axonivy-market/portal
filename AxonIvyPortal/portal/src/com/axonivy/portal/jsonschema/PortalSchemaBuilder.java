package com.axonivy.portal.jsonschema;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.jsonschema.generator.PortalSchemaGenerator;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;

public class PortalSchemaBuilder {
  private record SchemaDefinition(String fileName, Class<?> rootType) {}

  private static final List<SchemaDefinition> SCHEMAS = List.of(
      new SchemaDefinition("dashboard-schema.json", Dashboard.class),
      new SchemaDefinition("statistic-schema.json", Statistic.class),
      new SchemaDefinition("dashboard-template-schema.json", DashboardTemplate.class));

  public static void main(String[] args) {
    Path outDir = SystemUtils.getJavaIoTmpDir().toPath();
    if (args.length > 0) {
      outDir = Path.of(args[0]);
    }

    try {
      Files.createDirectories(outDir);
      var generator = new PortalSchemaGenerator();
      for (SchemaDefinition def : SCHEMAS) {
        writeSchema(generator, def, outDir);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
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
