package com.axonivy.portal.migration.dashboardconfiguration.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardConfigurationJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboardconfiguration.converter.JsonDashboardConfigurationConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonDashboardConfigurationMigrator {

  private final JsonNode node;
  private final DashboardConfigurationJsonVersion version;

  public JsonDashboardConfigurationMigrator(JsonNode node) {
    this.node = node;
    this.version = DashboardConfigurationJsonVersion.LATEST_VERSION;
  }

  public JsonDashboardConfigurationMigrator(JsonNode node, DashboardConfigurationJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read dashboard version
   * If version is null, assume that this configuration is created since version 10.0.0 (oldest version)
   * 
   * @param dashboard
   * @return
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(jsonNode -> jsonNode.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new DashboardConfigurationJsonVersion(field.asText()))
        .orElse(DashboardConfigurationJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    node.elements().forEachRemaining(config -> migrate(config));
    return node;
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
    Ivy.log().info("converting Portal dashboard configuration " + config.get("id") + " to version "+converter.version().getValue()
        +" using "+converter.getClass().getSimpleName());

    converter.convert(config);
    updateVersion(config);
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}