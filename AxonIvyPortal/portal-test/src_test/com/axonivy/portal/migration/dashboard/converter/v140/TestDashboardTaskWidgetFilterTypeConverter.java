package com.axonivy.portal.migration.dashboard.converter.v140;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Until 14.0.0 a task widget stored every case custom field filter as {@code custom_case}, even when
 * the column it was created from referred to the business case. These tests pin the correction down.
 */
class TestDashboardTaskWidgetFilterTypeConverter {

  private static final String CUSTOM_CASE = "custom_case";
  private static final String CUSTOM_BUSINESS_CASE = "custom_business_case";

  private DashboardTaskWidgetFilterTypeConverter converter;

  @BeforeEach
  void setUp() {
    converter = new DashboardTaskWidgetFilterTypeConverter();
  }

  @Test
  void version_isTheReleaseWhichIntroducesTheCorrection() {
    assertThat(converter.version().getValue()).isEqualTo("14.0.0");
  }

  @Test
  void filterOfBusinessCaseColumn_isRetypedToBusinessCase() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("CustomerName", CUSTOM_CASE))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void filterOfSubCaseColumn_staysSubCase() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_CASE)),
        filters(filter("CustomerName", CUSTOM_CASE))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_CASE);
  }

  @Test
  void untypedFilter_isTypedFromItsColumn() {
    ObjectNode filter = JsonNodeFactory.instance.objectNode().put("field", "CustomerName");
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter)));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  /**
   * The same case custom field can be added twice, once per case scope. A single filter cannot query
   * both, and every task has a business case while only some have a sub case, so the business case
   * wins.
   */
  @Test
  void fieldAddedAsBothColumns_resolvesToBusinessCase() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_CASE), column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("CustomerName", CUSTOM_CASE))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void fieldAddedAsBothColumns_resolvesToBusinessCase_regardlessOfColumnOrder() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE), column("CustomerName", CUSTOM_CASE)),
        filters(filter("CustomerName", CUSTOM_CASE))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void standardFilter_isNeverRetyped() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("name", "standard"), column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("name", "standard"))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo("standard");
  }

  /**
   * A task custom field and a case custom field can share a name. A {@code custom} filter belongs to
   * the task, so it must survive untouched even when a case column of the same name exists.
   */
  @Test
  void taskCustomFilter_isNeverRetyped() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("CustomerName", "custom"))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo("custom");
  }

  @Test
  void filterWithoutMatchingColumn_isLeftUntouched() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("OrderNumber", CUSTOM_CASE))));

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_CASE);
  }

  @Test
  void caseWidget_isLeftUntouched() {
    ObjectNode caseWidget = taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("CustomerName", CUSTOM_CASE)));
    caseWidget.put("type", "CASE");
    ObjectNode dashboard = dashboard(caseWidget);

    converter.convert(dashboard);

    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_CASE);
  }

  /**
   * A migrated dashboard is only written back on the next save, so the converter can run again on the
   * same node.
   */
  @Test
  void convert_isIdempotent() {
    ObjectNode dashboard = dashboard(taskWidget(
        columns(column("CustomerName", CUSTOM_BUSINESS_CASE)),
        filters(filter("CustomerName", CUSTOM_CASE))));

    converter.convert(dashboard);
    String afterFirstRun = dashboard.toString();
    converter.convert(dashboard);

    assertThat(dashboard.toString()).isEqualTo(afterFirstRun);
    assertThat(filterType(dashboard, 0)).isEqualTo(CUSTOM_BUSINESS_CASE);
  }

  @Test
  void widgetWithoutColumnsOrFilters_doesNotFail() {
    ObjectNode widgetWithoutColumns = JsonNodeFactory.instance.objectNode().put("type", "TASK");
    widgetWithoutColumns.set("filters", filters(filter("CustomerName", CUSTOM_CASE)));
    ObjectNode widgetWithoutFilters = JsonNodeFactory.instance.objectNode().put("type", "TASK");
    widgetWithoutFilters.set("columns", columns(column("CustomerName", CUSTOM_BUSINESS_CASE)));

    ObjectNode dashboard = dashboard(widgetWithoutColumns, widgetWithoutFilters,
        JsonNodeFactory.instance.objectNode().put("type", "TASK"));

    converter.convert(dashboard);

    assertThat(dashboard.get("widgets").get(0).get("filters").get(0).get("type").asText())
        .isEqualTo(CUSTOM_CASE);
  }

  @Test
  void dashboardWithoutWidgets_doesNotFail() {
    ObjectNode dashboard = JsonNodeFactory.instance.objectNode().put("id", "my-dashboard");

    converter.convert(dashboard);

    assertThat(dashboard.get("widgets")).isNull();
  }

  // ── fixtures ───────────────────────────────────────────────────────────────

  private static ObjectNode dashboard(ObjectNode... widgets) {
    ObjectNode dashboard = JsonNodeFactory.instance.objectNode();
    dashboard.put("id", "my-dashboard");
    dashboard.put("version", "13.2.0");
    ArrayNode widgetArray = dashboard.putArray("widgets");
    for (ObjectNode widget : widgets) {
      widgetArray.add(widget);
    }
    return dashboard;
  }

  private static ObjectNode taskWidget(ArrayNode columns, ArrayNode filters) {
    ObjectNode widget = JsonNodeFactory.instance.objectNode();
    widget.put("id", "task-widget-1");
    widget.put("type", "TASK");
    widget.set("columns", columns);
    widget.set("filters", filters);
    return widget;
  }

  private static ArrayNode columns(ObjectNode... columns) {
    ArrayNode array = JsonNodeFactory.instance.arrayNode();
    for (ObjectNode column : columns) {
      array.add(column);
    }
    return array;
  }

  private static ArrayNode filters(ObjectNode... filters) {
    ArrayNode array = JsonNodeFactory.instance.arrayNode();
    for (ObjectNode filter : filters) {
      array.add(filter);
    }
    return array;
  }

  private static ObjectNode column(String field, String type) {
    return JsonNodeFactory.instance.objectNode().put("field", field).put("type", type);
  }

  private static ObjectNode filter(String field, String type) {
    return JsonNodeFactory.instance.objectNode().put("field", field).put("type", type);
  }

  private static String filterType(JsonNode dashboard, int widgetIndex) {
    return dashboard.get("widgets").get(widgetIndex).get("filters").get(0).get("type").asText();
  }
}
