package com.axonivy.portal.migration.statistic.migrator;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.statistic.converter.JsonStatisticConverterFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonStatisticMigrator {

  private final JsonNode node;
  private final StatisticJsonVersion version;

  public JsonStatisticMigrator(JsonNode node) {
    this.node = removeDefaultChartsFromClientStatistic((ArrayNode) node);
    this.version = StatisticJsonVersion.LATEST_VERSION;
  }

  public JsonStatisticMigrator(JsonNode node, StatisticJsonVersion version) {
    this.node = removeDefaultChartsFromClientStatistic((ArrayNode) node);
    this.version = version;
  }

  /**
   * Read version If version is null, assume that this statistic is created since
   * version 11.3 (oldest version)
   * 
   * @return json version
   */
  private static AbstractJsonVersion readVersion(JsonNode node) {
    return Optional.ofNullable(node)
        .map(template -> template.get(AbstractJsonVersion.VERSION_FIELD_NAME))
        .map(field -> new StatisticJsonVersion(field.asText()))
        .orElse(StatisticJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    node.elements().forEachRemaining(template -> migrate(template));
    return node;
  }

  private void migrate(JsonNode chart) {
    var converters = JsonStatisticConverterFactory.getConverters(readVersion(chart)).stream()
        .filter(conv -> conv.version().compareTo(version) <= 0)
        .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(converters)) {
        converters.stream().forEachOrdered(converter -> run(converter, chart));
      }
  }

  private void run(IJsonConverter converter, JsonNode chart) {
    Ivy.log().info("Converting Portal statistic " + chart.get("id") + " to version " + converter.version().getValue()
        + " using "+ converter.getClass().getSimpleName());

    converter.convert(chart);
    updateVersion(chart);
  }

  private JsonNode removeDefaultChartsFromClientStatistic(ArrayNode nodes) {
    for (int i = nodes.size() - 1; i >= 0; i--) {
      JsonNode chart = nodes.get(i);
      int id = chart.path("id").asInt(Integer.MIN_VALUE);
        if (id >= 1 && id <= 11) {
          nodes.remove(i);

        }

    }
    return nodes;
  }

  private void updateVersion(JsonNode node) {
    TextNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new TextNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
