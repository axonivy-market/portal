package com.axonivy.portal.migration.dashboardtemplate.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboard.converter.JsonDashboardConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonDashboardTemplateMigrator {

  private final JsonNode node;
  private final DashboardTemplateJsonVersion version;

  public JsonDashboardTemplateMigrator(JsonNode node) {
    this.node = node;
    this.version = DashboardTemplateJsonVersion.LATEST_VERSION;
  }

  public JsonDashboardTemplateMigrator(JsonNode node, DashboardTemplateJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read version
   * If version is null, assume that this dashboard is created since version 10.0.0 (oldest version)
   * 
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(template -> template.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new DashboardTemplateJsonVersion(field.asText()))
        .orElse(DashboardTemplateJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    node.elements().forEachRemaining(template -> migrate(template));
    return node;
  }

  private void migrate(JsonNode template) {
    var converters = JsonDashboardConverterFactory.getConverters(readVersion(template)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, template));
      }
  }

  private void run(IJsonConverter converter, JsonNode template) {
    Ivy.log().info("Converting Portal dashboard template " + template.get("id") + " to version "+ converter.version().getValue()
        + " using "+ converter.getClass().getSimpleName());

    Optional.ofNullable(template).map(t -> t.get("dashboard")).ifPresent(dashboard -> {
      converter.convert(dashboard);
      updateVersion(dashboard);
    });
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
