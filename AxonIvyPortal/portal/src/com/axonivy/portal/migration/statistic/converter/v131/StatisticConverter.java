package com.axonivy.portal.migration.statistic.converter.v131;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.StatisticJsonVersion;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StatisticConverter implements IJsonConverter {

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
    convertDefaultChart(jsonNode);
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
      ((ObjectNode) jsonNode).set("numberChartConfig", numberChartConfigNode);
    }
  }

  private void convertDefaultChart(JsonNode jsonNode) {
    int id = jsonNode.path("id").asInt(Integer.MIN_VALUE);
    if (id < 1 || id > 11) {
      return;
    }

    if (jsonNode.has("chartTarget")) {
      ((ObjectNode) jsonNode).put("chartTarget", StringUtils.lowerCase(jsonNode.get("chartTarget").asText()));
    }

    if (jsonNode.has("aggregates")) {
      String aggregates = jsonNode.get("aggregates").asText();
      String fieldValue = "";
      if (aggregates.equalsIgnoreCase("priority")) {
        fieldValue = "priority";
      } else if (aggregates.equalsIgnoreCase("businessState") || aggregates.equalsIgnoreCase("state")) {
        fieldValue = "businessState";
      }

      if (StringUtils.isNotBlank(fieldValue)) {
        ObjectNode statisticAggregationNode = JsonNodeFactory.instance.objectNode();
        statisticAggregationNode.put("field", fieldValue);
        statisticAggregationNode.put("type", "standard");
        ((ObjectNode) jsonNode).set("statisticAggregation", statisticAggregationNode);
        ((ObjectNode) jsonNode).remove("aggregates");
      }
    }

    switch (id) {
    case 1, 3, 7, 9, 10 -> {
      if (jsonNode.has("filter")) {
        String filter = jsonNode.get("filter").asText();
        ArrayNode filtersArray = JsonNodeFactory.instance.arrayNode();

        for (String part : filter.split(",")) {
          if (part.startsWith("businessState:") || part.startsWith("state:") || part.startsWith("priority:")) {
            String[] tokens = part.split(":");
            String[] values = tokens[1].split(" ");

            ObjectNode filterNode = JsonNodeFactory.instance.objectNode();
            String fieldValue = "state";
            if (part.startsWith("priority:")) {
              fieldValue = "priority";
            }

            filterNode.put("field", fieldValue);
            filterNode.put("operator", "in");
            filterNode.put("type", "standard");
            ArrayNode valuesNode = JsonNodeFactory.instance.arrayNode();
            for (String val : values) {
              if (part.startsWith("state:") && "RUNNING".equalsIgnoreCase(val)) {
                valuesNode.add("OPEN");
              } else {
                valuesNode.add(val);
              }
            }
              filterNode.set("values", valuesNode);
              filtersArray.add(filterNode);
          } else if (part.equalsIgnoreCase("canWorkOn")) {
            ObjectNode filterNode = JsonNodeFactory.instance.objectNode();
            filterNode.put("field", "canWorkOn");
            filterNode.put("operator", "current_user");
            filterNode.put("type", "standard");
            filtersArray.add(filterNode);
          }
        }
        ((ObjectNode) jsonNode).set("filters", filtersArray);
        ((ObjectNode) jsonNode).remove("filter");
      }
    }
    default -> {
    }
    }
  }
}