package com.axonivy.portal.migration.dashboard.converter.v121;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Re-types each task-widget filter's case scope from its matching column, treating the column as the
 * source of truth: a filter on a business-case custom field is set to {@code custom_business_case}
 * (business case wins when a field is both a case and a business-case column). This is the migration
 * counterpart of {@code DashboardWidgetUtils.resolveCaseCustomColumnType} - it exists because the
 * persisted filter type is unreliable (case-custom filters are always stored as {@code custom_case}).
 *
 * <p>It only re-types filters in memory while a dashboard is read/migrated; it does not persist
 * anything back to storage (the migrator does not write on read). Only case-custom (or untyped)
 * filters are touched - STANDARD and task {@code custom} filters are left alone.
 */
public class DashboardTaskWidgetFilterTypeConverter implements IJsonConverter {

  private static final String COLUMNS = "columns";
  private static final String FILTERS = "filters";
  private static final String FIELD = "field";
  private static final String TYPE = "type";
  private static final String CUSTOM_CASE = DashboardColumnType.CUSTOM_CASE.getType();
  private static final String CUSTOM_BUSINESS_CASE = DashboardColumnType.CUSTOM_BUSINESS_CASE.getType();

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("12.1.0");
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
    Map<String, String> caseTypeByField = caseTypeByField((ArrayNode) columnsNode);
    if (caseTypeByField.isEmpty()) {
      return;
    }
    for (JsonNode filter : filtersNode) {
      if (!(filter instanceof ObjectNode)) {
        continue;
      }
      ObjectNode filterNode = (ObjectNode) filter;
      String field = textValue(filterNode, FIELD);
      String type = textValue(filterNode, TYPE);
      // Only re-type case-custom (or untyped) filters; never touch STANDARD or task CUSTOM filters.
      if (field == null || (type != null && !CUSTOM_CASE.equals(type) && !CUSTOM_BUSINESS_CASE.equals(type))) {
        continue;
      }
      String columnType = caseTypeByField.get(field);
      if (columnType != null) {
        filterNode.set(TYPE, new TextNode(columnType));
      }
    }
  }

  /**
   * Builds a field -&gt; case-column-type map from the widget columns. Business case wins when the same
   * field appears as both a CUSTOM_CASE and a CUSTOM_BUSINESS_CASE column.
   */
  private Map<String, String> caseTypeByField(ArrayNode columns) {
    Map<String, String> caseTypeByField = new HashMap<>();
    for (JsonNode column : columns) {
      String field = textValue(column, FIELD);
      String type = textValue(column, TYPE);
      if (field == null) {
        continue;
      }
      if (CUSTOM_BUSINESS_CASE.equals(type)) {
        caseTypeByField.put(field, CUSTOM_BUSINESS_CASE);
      } else if (CUSTOM_CASE.equals(type) && !CUSTOM_BUSINESS_CASE.equals(caseTypeByField.get(field))) {
        caseTypeByField.put(field, CUSTOM_CASE);
      }
    }
    return caseTypeByField;
  }

  private static String textValue(JsonNode node, String field) {
    JsonNode value = node.get(field);
    return value != null && value.isTextual() ? value.asText() : null;
  }
}
