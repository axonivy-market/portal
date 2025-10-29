package com.axonivy.portal.migration.thirdpartyapplication.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.ApplicationJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.thirdpartyapplication.converter.JsonThirdPartyApplicationConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

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
        .map(field -> new ApplicationJsonVersion(field.asText()))
        .orElse(ApplicationJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (node.isArray()) {
      node.elements().forEachRemaining(application -> migrate(application));
    } else {
      migrate(node);
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
    Ivy.log().info("Converting Portal third-party application " + application.path("id").asText("unknown") + " to version "
        + converter.version().getValue() + " using " + converter.getClass().getSimpleName());

    converter.convert(application);
    updateVersion(application);
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
