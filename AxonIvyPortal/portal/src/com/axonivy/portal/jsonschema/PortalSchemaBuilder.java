package com.axonivy.portal.jsonschema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.SystemUtils;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import tools.jackson.databind.node.ObjectNode;

public class PortalSchemaBuilder {
    public static void main(String[] args) {
      Path outDir = SystemUtils.getJavaIoTmpDir().toPath();
      if (args.length > 0) {
        outDir = Path.of(args[0]);
      }
      var schemaFile = outDir.resolve("dashboard-schema.json");
      System.out.println("writing schema" + schemaFile);
      try {
        Files.createDirectories(schemaFile.getParent());
        var generator = new PortalSchemaGenerator();
        ObjectNode schema = generator.generateSchema(Dashboard.class);
        Files.write(schemaFile, schema.toPrettyString().lines().toList());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
}
