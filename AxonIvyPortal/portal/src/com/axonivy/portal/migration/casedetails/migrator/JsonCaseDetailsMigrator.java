package com.axonivy.portal.migration.casedetails.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.CaseDetailsJsonVersion;
import com.axonivy.portal.migration.casedetails.converter.JsonCaseDetailsConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Migrates a {@code Portal.CaseDetails} value — an array of Case Details configurations — to the
 * latest schema. The node is mutated in place; nothing is written back to the variable, so an
 * administrator's file in the engine configuration directory is converted on every read and only
 * ever persisted when a user saves their own layout.
 */
public class JsonCaseDetailsMigrator {

  private final JsonNode node;
  private final CaseDetailsJsonVersion version;

  public JsonCaseDetailsMigrator(JsonNode node) {
    this(node, CaseDetailsJsonVersion.LATEST_VERSION);
  }

  public JsonCaseDetailsMigrator(JsonNode node, CaseDetailsJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read the configuration version. A configuration without one predates the versioning scheme, so
   * it is treated as the oldest version and every converter runs on it.
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(configuration -> configuration.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new CaseDetailsJsonVersion(field.asText()))
        .orElse(CaseDetailsJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (node.isArray()) {
      node.elements().forEachRemaining(this::migrate);
    } else {
      migrate(node);
    }
    return node;
  }

  private void migrate(JsonNode configuration) {
    var converters = JsonCaseDetailsConverterFactory.getConverters(readVersion(configuration)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(converters)) {
      converters.stream().forEachOrdered(converter -> run(converter, configuration));
    }
  }

  private void run(IJsonConverter converter, JsonNode configuration) {
    Ivy.log().info("Converting Portal case details " + configuration.get("id") + " to version "
        + converter.version().getValue() + " using " + converter.getClass().getSimpleName());

    converter.convert(configuration);
    updateVersion(configuration);
  }

  private void updateVersion(JsonNode node) {
    if (!node.isObject()) {
      return;
    }
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, new TextNode(version.getValue()));
  }
}
