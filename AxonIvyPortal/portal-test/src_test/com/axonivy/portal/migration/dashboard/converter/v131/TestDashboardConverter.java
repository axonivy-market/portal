package com.axonivy.portal.migration.dashboard.converter.v131;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

class TestDashboardConverter {

  private static final String DEFAULT_TASK_LIST_DASHBOARD = "default-task-list-dashboard";
  private static final String DEFAULT_CASE_LIST_DASHBOARD = "default-case-list-dashboard";

  private final ObjectMapper mapper = new ObjectMapper();
  private final DashboardConverter converter = new DashboardConverter();

  private ObjectNode dashboard(String id) {
    ObjectNode node = mapper.createObjectNode();
    node.put("id", id);
    return node;
  }

  @Test
  void version_returns13_1_0() {
    assertThat(converter.version().getValue()).isEqualTo("13.1.0");
  }

  @Test
  void convert_isTopMenuTrue_setsTopMenuDisplayTypeAndRemovesFlag() {
    ObjectNode dashboard = dashboard("dashboard-1");
    dashboard.put("isTopMenu", true);

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
    assertThat(dashboard.has("isTopMenu")).isFalse();
  }

  @Test
  void convert_isTopMenuFalse_setsSubMenuDisplayTypeAndRemovesFlag() {
    ObjectNode dashboard = dashboard("dashboard-1");
    dashboard.put("isTopMenu", false);

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
    assertThat(dashboard.has("isTopMenu")).isFalse();
  }

  @Test
  void convert_noIsTopMenuFlag_regularDashboard_defaultsToSubMenu() {
    ObjectNode dashboard = dashboard("dashboard-1");

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
  }

  @Test
  void convert_noIsTopMenuFlag_defaultTaskListDashboard_defaultsToTopMenu() {
    ObjectNode dashboard = dashboard(DEFAULT_TASK_LIST_DASHBOARD);

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
  }

  @Test
  void convert_noIsTopMenuFlag_defaultCaseListDashboard_defaultsToTopMenu() {
    ObjectNode dashboard = dashboard(DEFAULT_CASE_LIST_DASHBOARD);

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
  }

  @Test
  void convert_displayTypeAlreadyPresent_leftUntouched() {
    ObjectNode dashboard = dashboard("dashboard-1");
    dashboard.put("dashboardDisplayType", "hidden");

    converter.convert(dashboard);

    // Only fills in a default when genuinely missing - must not clobber an explicit user choice
    // (or the result of a previous migration pass) back to a default.
    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("hidden");
  }

  @Test
  void convert_defaultTaskListDashboardWithUnpinnedColumns_insertsPinColumnAtIndexOne() {
    ObjectNode dashboard = dashboard(DEFAULT_TASK_LIST_DASHBOARD);
    ArrayNode widgets = dashboard.putArray("widgets");
    ObjectNode widget = widgets.addObject();
    ArrayNode columns = widget.putArray("columns");
    columns.addObject().put("field", "name");
    columns.addObject().put("field", "state");

    converter.convert(dashboard);

    ArrayNode updatedColumns = (ArrayNode) dashboard.get("widgets").get(0).get("columns");
    assertThat(updatedColumns).hasSize(3);
    assertThat(updatedColumns.get(0).get("field").asText()).isEqualTo("name");
    assertThat(updatedColumns.get(1).get("field").asText()).isEqualTo("pin");
    assertThat(updatedColumns.get(2).get("field").asText()).isEqualTo("state");
  }

  @Test
  void convert_defaultCaseListDashboardWithPinAlreadyPresent_notDuplicated() {
    ObjectNode dashboard = dashboard(DEFAULT_CASE_LIST_DASHBOARD);
    ArrayNode widgets = dashboard.putArray("widgets");
    ObjectNode widget = widgets.addObject();
    ArrayNode columns = widget.putArray("columns");
    columns.addObject().put("field", "name");
    columns.addObject().put("field", "pin");

    converter.convert(dashboard);

    ArrayNode updatedColumns = (ArrayNode) dashboard.get("widgets").get(0).get("columns");
    assertThat(updatedColumns).hasSize(2);
  }

  @Test
  void convert_nonDefaultDashboardWithColumns_noPinColumnInserted() {
    ObjectNode dashboard = dashboard("dashboard-1");
    ArrayNode widgets = dashboard.putArray("widgets");
    ObjectNode widget = widgets.addObject();
    ArrayNode columns = widget.putArray("columns");
    columns.addObject().put("field", "name");

    converter.convert(dashboard);

    ArrayNode updatedColumns = (ArrayNode) dashboard.get("widgets").get(0).get("columns");
    assertThat(updatedColumns).hasSize(1);
  }

  @Test
  void convert_defaultTaskListDashboardWithoutWidgets_doesNotThrow() {
    ObjectNode dashboard = dashboard(DEFAULT_TASK_LIST_DASHBOARD);

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
  }

  @Test
  void convert_defaultTaskListDashboardWidgetWithoutColumns_doesNotThrow() {
    ObjectNode dashboard = dashboard(DEFAULT_TASK_LIST_DASHBOARD);
    ArrayNode widgets = dashboard.putArray("widgets");
    widgets.addObject().put("id", "widget-1");

    converter.convert(dashboard);

    JsonNode widget = dashboard.get("widgets").get(0);
    assertThat(widget.has("columns")).isFalse();
    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
  }
}
