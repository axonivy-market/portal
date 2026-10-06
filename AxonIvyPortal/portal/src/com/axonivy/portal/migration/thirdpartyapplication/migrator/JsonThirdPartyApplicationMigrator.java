package com.axonivy.portal.migration.thirdpartyapplication.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.ApplicationJsonVersion;
import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.thirdpartyapplication.converter.JsonThirdPartyApplicationConverterFactory;

import ch.ivyteam.ivy.environment.Ivy;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;
import tools.jackson.databind.node.StringNode;

public class JsonThirdPartyApplicationMigrator {

  private final JsonNode node;
  private final ApplicationJsonVersion version;

  public JsonThirdPartyApplicationMigrator(JsonNode node) {
    this.node = node;
    this.version = ApplicationJsonVersion.LATEST_VERSION;
  }

  public JsonThirdPartyApplicationMigrator(JsonNode node, ApplicationJsonVersion version) {
    this.node = node;
    this.version = version;
  }

  /**
   * Read version
   * If version is null, assume that this application is created since version 10.0.0 (oldest version)
   * 
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(appNode -> appNode.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new ApplicationJsonVersion(field.asString()))
        .orElse(ApplicationJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (JsonListWrapper.isListWrapper(node)) {
      // Canonical shape: {"version": "...", "items": [...]}. Checked before the legacy
      // dynamic-root-key heuristic below, since the wrapper's own "version" field would
      // otherwise be treated as a single application. Once wrapped, the wrapper's own version
      // is the sole gate - per-item version is never read again and items are not re-run
      // through the per-item converter chain.
      return node;
    }
    if (node.isArray()) {
      node.values().forEach(application -> migrate(application));
    } else if (node.isObject()) {
      var propertyNames = node.propertyNames();
      if (!propertyNames.isEmpty()) {
        String firstField = propertyNames.iterator().next();
        JsonNode firstValue = node.get(firstField);
        if (firstValue != null && firstValue.isArray()
            && (firstValue.isEmpty() || firstValue.get(0).isObject())) {
          // Handle legacy root-wrapped arrays like {"third-party-application": [...]} — key is read dynamically
          firstValue.values().forEach(application -> migrate(application));
        } else {
          // Single application object
          migrate(node);
        }
      }
      // else: empty object {} — nothing to migrate
    }
    return node;
  }

  private void migrate(JsonNode application) {
    var converters = JsonThirdPartyApplicationConverterFactory.getConverters(readVersion(application)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, application));
      }
  }

  private void run(IJsonConverter converter, JsonNode application) {
    Ivy.log().info("Converting Portal third-party application " + application.path("id").asString("unknown") + " to version "
        + converter.version().getValue() + " using " + converter.getClass().getSimpleName());

    converter.convert(application);
    updateVersion(application);
  }

  private void updateVersion(JsonNode node) {
    StringNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new StringNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
