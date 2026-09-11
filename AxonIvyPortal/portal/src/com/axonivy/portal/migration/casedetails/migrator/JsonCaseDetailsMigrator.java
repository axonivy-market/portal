package com.axonivy.portal.migration.casedetails.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.CaseDetailsJsonVersion;
import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.migration.casedetails.converter.JsonCaseDetailsConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

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
   * Read case details version
   * If version is null, assume that this configuration is created since the oldest version
   *
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(configuration -> configuration.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new CaseDetailsJsonVersion(field.asText()))
        .orElse(CaseDetailsJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (JsonListWrapper.isListWrapper(node)) {
      // Canonical shape: {"version": "...", "items": [...]}. Once wrapped, the wrapper's own
      // version is the sole gate - per-item version is never read again and items are not
      // re-run through the per-item converter chain.
      return node;
    }
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
