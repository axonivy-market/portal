package com.axonivy.portal.migration.dashboard.converter.v140;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

/**
 * v140's {@link DashboardConverter} intentionally mirrors
 * {@link com.axonivy.portal.migration.dashboard.converter.v131.DashboardConverter}'s logic exactly
 * (re-registered at 14.0.0) - see that class' test for the full behavioral coverage. This class
 * focuses on confirming the version and re-verifying the same core behavior from the v140 package.
 */
class TestDashboardConverter {

  private static final String DEFAULT_TASK_LIST_DASHBOARD = "default-task-list-dashboard";

  private final ObjectMapper mapper = new ObjectMapper();
  private final DashboardConverter converter = new DashboardConverter();

  private ObjectNode dashboard(String id) {
    ObjectNode node = mapper.createObjectNode();
    node.put("id", id);
    return node;
  }

  @Test
  void version_returns14_0_0() {
    assertThat(converter.version().getValue()).isEqualTo("14.0.0");
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
  void convert_noIsTopMenuFlag_regularDashboard_defaultsToSubMenu() {
    ObjectNode dashboard = dashboard("dashboard-1");

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
  }

  @Test
  void convert_displayTypeAlreadyPresent_leftUntouched() {
    ObjectNode dashboard = dashboard("dashboard-1");
    dashboard.put("dashboardDisplayType", "hidden");

    converter.convert(dashboard);

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
    assertThat(updatedColumns.get(1).get("field").asText()).isEqualTo("pin");
  }

  @Test
  void convert_defaultTaskListDashboardWidgetWithoutColumns_doesNotThrow() {
    ObjectNode dashboard = dashboard(DEFAULT_TASK_LIST_DASHBOARD);
    ArrayNode widgets = dashboard.putArray("widgets");
    widgets.addObject().put("id", "widget-1");

    converter.convert(dashboard);

    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
  }
}
