package com.axonivy.portal.migration.dashboard.migrator;

import static org.assertj.core.api.Assertions.assertThat;

import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * {@code @IvyTest} is required because {@link JsonDashboardMigrator} logs via {@code Ivy.log()}
 * while running converters.
 */
@IvyTest
class TestJsonDashboardMigrator {

  private final ObjectMapper mapper = new ObjectMapper();

  private ObjectNode legacyDashboardWithColumnlessTaskWidget(String id) {
    ObjectNode dashboard = mapper.createObjectNode();
    dashboard.put("id", id);
    ArrayNode widgets = dashboard.putArray("widgets");
    ObjectNode taskWidget = widgets.addObject();
    taskWidget.put("type", "task");
    taskWidget.put("id", "task_1");
    // Deliberately no "columns" field - the out-of-the-box state for a new widget
    // (TaskDashboardWidget omits it entirely from JSON when empty, see @JsonInclude(NON_EMPTY)),
    // which used to make v113's task/case widget converters throw NoSuchElementException.
    return dashboard;
  }

  @Test
  void migrate_legacyDashboardWidgetWithoutColumns_doesNotThrow() {
    JsonNode node = legacyDashboardWithColumnlessTaskWidget("dashboard-1");

    JsonNode result = new JsonDashboardMigrator(node).migrate();

    assertThat(result.get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
    assertThat(result.get("version").asText()).isEqualTo(DashboardJsonVersion.LATEST_VERSION.getValue());
  }

  @Test
  void migrate_wrapperShape_skipsVersionGatedChain_butStillRunsUnconditionalSafetyNets() {
    ObjectNode wrapper = mapper.createObjectNode();
    wrapper.put("version", "14.0.0");
    ArrayNode items = wrapper.putArray("items");
    items.add(legacyDashboardWithColumnlessTaskWidget("dashboard-1"));
    ObjectNode staleStateDashboard = mapper.createObjectNode();
    staleStateDashboard.put("id", "dashboard-2");
    ArrayNode widgets = staleStateDashboard.putArray("widgets");
    ObjectNode taskWidget = widgets.addObject();
    taskWidget.put("type", "task");
    taskWidget.put("id", "task_1");
    ArrayNode columns = taskWidget.putArray("columns");
    ObjectNode stateColumn = columns.addObject();
    stateColumn.put("field", "state");
    ArrayNode filterList = stateColumn.putArray("filterList");
    filterList.add("PARKED");
    filterList.add("RESUMED");
    items.add(staleStateDashboard);

    JsonNode result = new JsonDashboardMigrator(wrapper).migrate();

    JsonNode dashboard = result.get("items").get(0);
    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
    assertThat(dashboard.has("version")).isFalse();

    JsonNode convertedFilterList =
        result.get("items").get(1).get("widgets").get(0).get("columns").get(0).get("filterList");
    assertThat(convertedFilterList).extracting(JsonNode::asText).containsExactlyInAnyOrder("OPEN", "IN_PROGRESS");
  }
}
