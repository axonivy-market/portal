package com.axonivy.portal.migration.statistic.converter.v131;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ClientStatisticConverter implements IJsonConverter {

  @Override
  public AbstractJsonVersion version() {
    return new StatisticJsonVersion("13.1.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    ChartType chartType = EnumUtils.getEnum(ChartType.class, StringUtils.upperCase(jsonNode.get("chartType").asText()));
    if (chartType == ChartType.NUMBER) {
      updateNumberChartConfig((ObjectNode) jsonNode);
    }
  }

  private void updateNumberChartConfig(ObjectNode jsonNode) {
    if (jsonNode.has("hideLabel")) {
      ObjectNode numberChartConfigNode;
      if (!jsonNode.has("numberChartConfig")) {
        numberChartConfigNode = JsonNodeFactory.instance.objectNode();
      } else {
        numberChartConfigNode = (ObjectNode) jsonNode.get("numberChartConfig");
      }
      numberChartConfigNode.set("hideLabel", jsonNode.get("hideLabel"));
    }
  }
}