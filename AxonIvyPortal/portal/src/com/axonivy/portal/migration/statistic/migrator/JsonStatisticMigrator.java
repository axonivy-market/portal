package com.axonivy.portal.migration.statistic.migrator;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.statistic.converter.JsonStatisticConverterFactory;

import ch.ivyteam.ivy.environment.Ivy;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;
import tools.jackson.databind.node.StringNode;

public class JsonStatisticMigrator {

  private final JsonNode node;
  private final StatisticJsonVersion version;

  public JsonStatisticMigrator(JsonNode node) {
    this.node = node;
    this.version = StatisticJsonVersion.LATEST_VERSION;
  }

  public JsonStatisticMigrator(JsonNode node, StatisticJsonVersion version) {
    this.node = node;
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
        .map(field -> new StatisticJsonVersion(field.asString()))
        .orElse(StatisticJsonVersion.OLDEST_VERSION);
  }

  public JsonNode migrate() {
    if (JsonListWrapper.isListWrapper(node)) {
      // Canonical shape: {"version": "...", "items": [...]}. Once wrapped, the wrapper's own
      // version is the sole gate - per-item version is never read again and items are not
      // re-run through the per-item converter chain (including the legacy default-chart
      // cleanup below).
      return node;
    }
    Ivy.log().info("Converting Portal original statistic charts: " + collectChartIds((ArrayNode) node));
    removeDefaultChartsFromClientStatistic((ArrayNode) node);
    node.values().forEach(template -> migrate(template));
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

  private String collectChartIds(ArrayNode nodes) {
    return StreamSupport.stream(nodes.spliterator(), false)
        .map(chart -> chart.path("id").asString())
        .collect(Collectors.joining(", "));
  }

  private void removeDefaultChartsFromClientStatistic(ArrayNode nodes) {
    for (int i = nodes.size() - 1; i >= 0; i--) {
      JsonNode chart = nodes.get(i);
      int id = chart.path("id").asInt(Integer.MIN_VALUE);
        if (id >= 1 && id <= 11) {
          nodes.remove(i);
        }
    }
  }

  private void updateVersion(JsonNode node) {
    StringNode versionNode = Optional.ofNullable(version)
        .map(v -> v.getValue())
        .map(val -> new StringNode(val)).get();
    ((ObjectNode) node).set(AbstractJsonVersion.VERSION_FIELD_NAME, versionNode);
  }
}
