package com.axonivy.portal.migration.dashboardtemplate.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.migration.common.BusinessStateMigrationUtils;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.dashboardtemplate.converter.JsonDashboardTemplateConverterFactory;
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

  /** Legacy templates nest widgets/version under "dashboard"; current-schema templates carry them
   * directly. Assuming only one shape would silently skip every template using the other. */
  private static JsonNode targetNode(JsonNode template) {
    if (template == null) {
      return null;
    }
    JsonNode dashboard = template.get("dashboard");
    return dashboard != null ? dashboard : template;
  }

  /**
   * Read version.
   * If version is null, assume that this template is created since version 10.0.0 (oldest version).
   *
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode template) {
    return Optional.ofNullable(targetNode(template))
        .map(t -> t.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new DashboardTemplateJsonVersion(field.asText()))
        .orElse(DashboardTemplateJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (JsonListWrapper.isListWrapper(node)) {
      // Wrapper's version gates the full per-item converter chain (per-item version is never read
      // again once wrapped), but the unconditional safety nets below must still run on every read.
      node.get("items").forEach(this::ensureSafetyNets);
      return node;
    }
    if (node.isArray()) {
      node.elements().forEachRemaining(template -> migrate(template));
    } else {
      migrate(node);
    }
    return node;
  }

  private void migrate(JsonNode template) {
    var converters = JsonDashboardTemplateConverterFactory.getConverters(readVersion(template)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, template));
      }
      ensureSafetyNets(template);
  }

  private void ensureSafetyNets(JsonNode template) {
    BusinessStateMigrationUtils.ensureTaskAndCaseStateFiltersCurrent(targetNode(template));
  }

  private void run(IJsonConverter converter, JsonNode template) {
    Ivy.log().info("Converting Portal dashboard template " + template.get("id") + " to version "+ converter.version().getValue()
        + " using "+ converter.getClass().getSimpleName());

    Optional.ofNullable(targetNode(template)).ifPresent(dashboard -> {
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
