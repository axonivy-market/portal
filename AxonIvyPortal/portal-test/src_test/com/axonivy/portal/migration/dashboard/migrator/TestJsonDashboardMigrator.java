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
  void migrate_wrapperShape_isReturnedCompletelyUnchanged() {
    // Once wrapped, the wrapper's own version is the sole gate for this collection format -
    // per-item version is never read again, and items are NOT re-run through the per-item
    // converter chain. Required, not optional: per-item version is stripped once wrapped (see
    // JsonListWrapper), so without this short-circuit every read of already-current data would
    // see an absent version, fall back to OLDEST, and re-run every converter unconditionally
    // forever.
    ObjectNode wrapper = mapper.createObjectNode();
    wrapper.put("version", "14.0.0");
    ArrayNode items = wrapper.putArray("items");
    items.add(legacyDashboardWithColumnlessTaskWidget("dashboard-1"));

    JsonNode result = new JsonDashboardMigrator(wrapper).migrate();

    JsonNode dashboard = result.get("items").get(0);
    assertThat(dashboard.has("dashboardDisplayType")).isFalse();
    assertThat(dashboard.has("version")).isFalse();
  }
}
