package com.axonivy.portal.migration.dashboard.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboard.converter.JsonDashboardConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonDashboardMigrator {

  private final JsonNode node;
  private final DashboardJsonVersion version;

  public JsonDashboardMigrator(JsonNode node) {
    this.node = node;
    this.version = DashboardJsonVersion.LATEST_VERSION;
  }

  public JsonDashboardMigrator(JsonNode node, DashboardJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read dashboard version
   * If version is null, assume that this dashboard is created since version 10.0.0 (oldest version)
   * 
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(jsonNode -> jsonNode.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new DashboardJsonVersion(field.asText()))
        .orElse(DashboardJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    
    if (node.isArray()) {
      Ivy.log().error("Migrating dashboard array with size: {0}", node.size());
      node.elements().forEachRemaining(dashboard -> migrate(dashboard));

      
    } else {
      Ivy.log().error("Migrating single dashboard");
      migrate(node);
    }
    return node;
  }

  private void migrate(JsonNode dashboard) {
    //readVersion(dashboard);
    if (dashboard.isArray()) {
      Ivy.log().error("Dashboard is an array, size: {0}", dashboard.size());
      dashboard.elements().forEachRemaining(dashboardNode -> migrate(dashboardNode));
      return;
    }
    Ivy.log().error("Migrating dashboard version: {0}, full content: {1}", dashboard.get(AbstractJsonVersion.VERSION_FIELD_NAME), dashboard.toString().substring(0, 100));
    var converters = JsonDashboardConverterFactory.getConverters(readVersion(dashboard)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, dashboard));
      }
    Ivy.log().error("Migrating JSONNode OK");
  }

  private void run(IJsonConverter converter, JsonNode dashboard) {
    Ivy.log().info("Converting Portal dashboard " + dashboard.get("id") + " to version "
        + converter.version().getValue() + " using " + converter.getClass().getSimpleName());

    converter.convert(dashboard);
    updateVersion(dashboard);
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
