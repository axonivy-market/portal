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

/**
 * Re-types the predefined filters of a task widget which filter on a case custom field.
 * <p>
 * Until 14.0.0 every case custom field filter of a task widget was stored as
 * {@code custom_case}, no matter whether the column it was created from referred to the sub case or
 * to the business case. The type now decides which case the filter queries, so it is corrected from
 * the columns of the very same widget.
 */
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
      // Only re-type filters which carry no type at all, or one of the two case custom types. Never
      // touch STANDARD or task CUSTOM filters, nor a type this converter does not recognize.
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

  /**
   * Maps each case custom field of the widget to the case it refers to. A field which is added both
   * as a sub case and as a business case column is ambiguous; the business case wins, because that is
   * the case a task always has.
   */
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

  /**
   * Dashboard JSON is deserialized with {@code ACCEPT_CASE_INSENSITIVE_ENUMS}, and the task widget
   * documentation spells column types upper case, so a hand written configuration may use either
   * case. Parse the value instead of comparing the raw text.
   */
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
