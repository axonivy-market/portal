package com.axonivy.portal.migration.dashboardfilter.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboardfilter.converter.JsonDashboardFilterConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonDashboardFilterMigrator {

  private final JsonNode node;
  private final DashboardFilterJsonVersion version;

  public JsonDashboardFilterMigrator(JsonNode node) {
    this.node = node;
    this.version = DashboardFilterJsonVersion.LATEST_VERSION;
  }

  public JsonDashboardFilterMigrator(JsonNode node, DashboardFilterJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read dashboard version
   * If version is null, assume that this configuration is created since version 10.0.0 (oldest version)
   * 
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(jsonNode -> jsonNode.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new DashboardFilterJsonVersion(field.asText()))
        .orElse(DashboardFilterJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    node.elements().forEachRemaining(config -> migrate(config));
    return node;
  }

  private void migrate(JsonNode node) {
    var converters = JsonDashboardFilterConverterFactory.getConverters(readVersion(node)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, node));
      }
  }

  private void run(IJsonConverter converter, JsonNode config) {
    Ivy.log().info("Converting Portal dashboard configuration " + config.get("id") + " to version "+converter.version().getValue()
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