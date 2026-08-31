package com.axonivy.portal.migration.taskdetails.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.TaskDetailsJsonVersion;
import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.taskdetails.converter.JsonTaskDetailsConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonTaskDetailsMigrator {

  private final JsonNode node;
  private final TaskDetailsJsonVersion version;

  public JsonTaskDetailsMigrator(JsonNode node) {
    this(node, TaskDetailsJsonVersion.LATEST_VERSION);
  }

  public JsonTaskDetailsMigrator(JsonNode node, TaskDetailsJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read task details version
   * If version is null, assume that this configuration is created since the oldest version
   *
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(configuration -> configuration.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new TaskDetailsJsonVersion(field.asText()))
        .orElse(TaskDetailsJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (node.isArray()) {
      node.elements().forEachRemaining(this::migrate);
    } else if (JsonListWrapper.isListWrapper(node)) {
      // Canonical shape: {"version": "...", "items": [...]}. The wrapper-level "version"
      // tracks the JSON collection format, not any single configuration's migration version,
      // so only the items are migrated, not the wrapper itself.
      node.get(JsonListWrapper.ITEMS_FIELD_NAME).elements().forEachRemaining(this::migrate);
    } else {
      migrate(node);
    }
    return node;
  }

  private void migrate(JsonNode configuration) {
    var converters = JsonTaskDetailsConverterFactory.getConverters(readVersion(configuration)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(converters)) {
      converters.stream().forEachOrdered(converter -> run(converter, configuration));
    }
  }

  private void run(IJsonConverter converter, JsonNode configuration) {
    Ivy.log().info("Converting Portal task details " + configuration.get("id") + " to version "
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
