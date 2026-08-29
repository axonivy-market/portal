package com.axonivy.portal.migration.dashboard.converter.v140;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardTaskWidgetFilterTypeConverter implements IJsonConverter {

  private static final String COLUMNS = "columns";
  private static final String FILTERS = "filters";
  private static final String FIELD = "field";
  private static final String TYPE = "type";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("14.0.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    List<JsonNode> taskWidgets = new JsonWidgetSearch(jsonNode).type(DashboardWidgetType.TASK.name()).findWidgets();
    for (JsonNode taskWidget : taskWidgets) {
      convertWidgetFilters(taskWidget);
    }
  }

  private void convertWidgetFilters(JsonNode taskWidget) {
    JsonNode filtersNode = taskWidget.get(FILTERS);
    JsonNode columnsNode = taskWidget.get(COLUMNS);
    if (filtersNode == null || !filtersNode.isArray() || columnsNode == null || !columnsNode.isArray()) {
      return;
    }
    Map<String, DashboardColumnType> caseTypeByField = caseTypeByField((ArrayNode) columnsNode);
    if (caseTypeByField.isEmpty()) {
      return;
    }
    for (JsonNode filter : filtersNode) {
      if (!(filter instanceof ObjectNode filterNode)) {
        continue;
      }
      String field = textValue(filterNode, FIELD);
      String rawType = textValue(filterNode, TYPE);
      DashboardColumnType type = parseType(rawType);
      if (field == null || (StringUtils.isNotBlank(rawType) && type != DashboardColumnType.CUSTOM_CASE
          && type != DashboardColumnType.CUSTOM_BUSINESS_CASE)) {
        continue;
      }
      DashboardColumnType columnType = caseTypeByField.get(field);
      if (columnType != null) {
        filterNode.set(TYPE, new TextNode(columnType.getType()));
      }
    }
  }

  private Map<String, DashboardColumnType> caseTypeByField(ArrayNode columns) {
    Map<String, DashboardColumnType> caseTypeByField = new HashMap<>();
    for (JsonNode column : columns) {
      String field = textValue(column, FIELD);
      DashboardColumnType type = parseType(textValue(column, TYPE));
      if (field == null) {
        continue;
      }
      if (type == DashboardColumnType.CUSTOM_BUSINESS_CASE) {
        caseTypeByField.put(field, DashboardColumnType.CUSTOM_BUSINESS_CASE);
      } else if (type == DashboardColumnType.CUSTOM_CASE
          && caseTypeByField.get(field) != DashboardColumnType.CUSTOM_BUSINESS_CASE) {
        caseTypeByField.put(field, DashboardColumnType.CUSTOM_CASE);
      }
    }
    return caseTypeByField;
  }

  private static DashboardColumnType parseType(String type) {
    if (StringUtils.isBlank(type)) {
      return null;
    }
    try {
      return DashboardColumnType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  private static String textValue(JsonNode node, String field) {
    JsonNode value = node.get(field);
    return value != null && value.isTextual() ? value.asText() : null;
  }
}
