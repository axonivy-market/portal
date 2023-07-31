package com.axonivy.portal.migration.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.JsonVersion;
import com.axonivy.portal.migration.converter.IJsonConverter;
import com.axonivy.portal.migration.converter.JsonDashboardConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.log.Logger;

public class JsonDashboardTemplateMigrator {

  private static final Logger LOGGER = Logger.getLogger(JsonDashboardTemplateMigrator.class);
  private static final String V_10 = "10.0.0";

  private final JsonNode node;
  private final JsonVersion version;
  private String name;

  public JsonDashboardTemplateMigrator(JsonNode node) {
    this.node = node;
    this.version = JsonVersion.LATEST;
  }

  public JsonDashboardTemplateMigrator(JsonNode node, JsonVersion version) {
    this.node = node;
    this.version = version;
  }

  public JsonDashboardTemplateMigrator setName(String dashboardsName) {
    this.name = dashboardsName;
    return this;
  }

  /**
   * Read version
   * If version is null, assume that this dashboard is created since version 10.0.0 (oldest version)
   * 
   * @param dashboard
   * @return
   */
  private static JsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(jsonNode -> jsonNode.get("dashboard"))
        .map(dashboard -> dashboard.get(JsonVersion.VERSION_FIELD_NAME))
        .map(field -> new JsonVersion(field.asText()))
        .orElse(new JsonVersion(V_10));
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
    LOGGER.info("converting " + name + " '" + template.get("id") + "' to version "+converter.version()
      +" using "+converter.getClass().getSimpleName());

    Optional.ofNullable(template).map(t -> t.get("dashboard")).ifPresent(dashboard -> {
      converter.convert(dashboard);
      updateVersion(dashboard);
    });
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(JsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
