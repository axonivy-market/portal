package com.axonivy.portal.migration.statistic.converter.v131;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.enums.ChartType;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class StatisticChartConverter implements IJsonConverter {

  private static final List<String> DEFAULT_COLORS = Arrays.asList("#6299f7", "#8dc261", "#98bffa", "#bee3cb", "#c8befa", "#f5bf9f", "#f8da96", "#f9908c");

  @Override
  public AbstractJsonVersion version() {
    return new StatisticJsonVersion("13.1.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    ChartType chartType = EnumUtils.getEnum(ChartType.class, StringUtils.upperCase(jsonNode.get("chartType").asText()));
    switch (chartType) {
    case ChartType.PIE:
      addPieChartConfig((ObjectNode) jsonNode);
      break;
//    case ChartType.NUMBER:
//      addChartConfig((ObjectNode) jsonNode);
//      break;
    default:
      break;
    }
  }

  private void addPieChartConfig(ObjectNode jsonNode) {
    if (!jsonNode.has("pieChartConfig")) {
      List<TextNode> colorNodes = DEFAULT_COLORS.stream().map(color -> new TextNode(color)).collect(Collectors.toList());
      ArrayNode colorArrayNode = JsonNodeFactory.instance.arrayNode();
      colorArrayNode.addAll(colorNodes);

      ObjectNode bgColorNode = JsonNodeFactory.instance.objectNode();
      bgColorNode.set("backgroundColors", colorArrayNode);

      jsonNode.set("pieChartConfig", bgColorNode);
    }
  }

  private void addChartConfig(ObjectNode jsonNode) {
    if (!jsonNode.has("pieChartConfig")) {
      List<TextNode> colorNodes = DEFAULT_COLORS.stream().map(color -> new TextNode(color)).collect(Collectors.toList());
      ArrayNode colorArrayNode = JsonNodeFactory.instance.arrayNode();
      colorArrayNode.addAll(colorNodes);

      ObjectNode bgColorNode = JsonNodeFactory.instance.objectNode();
      bgColorNode.set("backgroundColors", colorArrayNode);

      jsonNode.set("pieChartConfig", bgColorNode);
    }
  }
}