package com.axonivy.portal.migration.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.JsonVersion;
import com.axonivy.portal.migration.converter.IJsonConverter;
import com.axonivy.portal.migration.converter.JsonDashboardConfigurationConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.log.Logger;

public class JsonDashboardConfigurationMigrator {

  private static final Logger LOGGER = Logger.getLogger(JsonDashboardMigrator.class);
  private static final String V_10 = "10.0.0";

  private final JsonNode main;
  private final JsonVersion version;
  private String name;

  public JsonDashboardConfigurationMigrator(JsonNode main) {
    this.main = main;
    this.version = JsonVersion.LATEST;
  }

  public JsonDashboardConfigurationMigrator(JsonNode main, JsonVersion version) {
    this.main = main;
    this.version = version;
  }

  public JsonDashboardConfigurationMigrator setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Read dashboard version
   * If version is null, assume that this configuration is created since version 10.0.0 (oldest version)
   * 
   * @param dashboard
   * @return
   */
  private static JsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(jsonNode -> jsonNode.get(JsonVersion.VERSION_FIELD_NAME))
        .map(field -> new JsonVersion(field.asText()))
        .orElse(new JsonVersion(V_10));
  }

  public String migrate() {
    main.elements().forEachRemaining(config -> migrate(config));
    return main.toString();
  }

  private void migrate(JsonNode node) {
    var converters = JsonDashboardConfigurationConverterFactory.getConverters(readVersion(node)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, node));
      }
  }

  private void run(IJsonConverter converter, JsonNode config) {
    LOGGER.info("converting " + name + " '" + config.get("id") + "' to version "+converter.version()
      +" using "+converter.getClass().getSimpleName());

    converter.convert(config);
    updateVersion(config);
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(JsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}