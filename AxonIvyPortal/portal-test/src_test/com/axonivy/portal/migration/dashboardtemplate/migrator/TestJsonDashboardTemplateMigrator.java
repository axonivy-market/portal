package com.axonivy.portal.migration.dashboardtemplate.migrator;

import static org.assertj.core.api.Assertions.assertThat;

import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/** {@code @IvyTest} is required because {@link JsonDashboardTemplateMigrator} logs via {@code Ivy.log()}
 * while running converters. */
@IvyTest
class TestJsonDashboardTemplateMigrator {

  private final ObjectMapper mapper = new ObjectMapper();

  private ObjectNode template(String templateId, String dashboardId, Boolean isTopMenu, String dashboardVersion) {
    ObjectNode template = mapper.createObjectNode();
    template.put("id", templateId);
    ObjectNode dashboard = template.putObject("dashboard");
    dashboard.put("id", dashboardId);
    if (isTopMenu != null) {
      dashboard.put("isTopMenu", isTopMenu);
    }
    if (dashboardVersion != null) {
      dashboard.put("version", dashboardVersion);
    }
    return template;
  }

  private ObjectNode taskWidgetWithStateFilterList(ObjectNode dashboard, String... rawStateValues) {
    ArrayNode widgets = dashboard.putArray("widgets");
    ObjectNode taskWidget = widgets.addObject();
    taskWidget.put("type", "task");
    taskWidget.put("id", "task_1");
    ArrayNode columns = taskWidget.putArray("columns");
    ObjectNode stateColumn = columns.addObject();
    stateColumn.put("field", "state");
    ArrayNode filterList = stateColumn.putArray("filterList");
    for (String value : rawStateValues) {
      filterList.add(value);
    }
    return taskWidget;
  }

  private ObjectNode taskWidgetWithStateFilterValues(ObjectNode dashboard, String... rawStateValues) {
    ArrayNode widgets = dashboard.putArray("widgets");
    ObjectNode taskWidget = widgets.addObject();
    taskWidget.put("type", "task");
    taskWidget.put("id", "task_1");
    taskWidget.putArray("columns").addObject().put("field", "state");
    ArrayNode filters = taskWidget.putArray("filters");
    ObjectNode stateFilter = filters.addObject();
    stateFilter.put("field", "state");
    stateFilter.put("operator", "in");
    stateFilter.put("type", "standard");
    ArrayNode values = stateFilter.putArray("values");
    for (String value : rawStateValues) {
      values.add(value);
    }
    return taskWidget;
  }

  @Test
  void migrate_singleTemplate_noVersion_runsFullConverterChainAndStampsLatestVersion() {
    JsonNode node = template("template-1", "dashboard-1", true, null);

    JsonNode result = new JsonDashboardTemplateMigrator(node).migrate();

    JsonNode dashboard = result.get("dashboard");
    assertThat(dashboard.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
    assertThat(dashboard.has("isTopMenu")).isFalse();
    assertThat(dashboard.get("version").asText()).isEqualTo(DashboardTemplateJsonVersion.LATEST_VERSION.getValue());
  }

  @Test
  void migrate_singleTemplate_alreadyAtLatestVersion_doesNotReRunVersionGatedConverters() {
    JsonNode node =
        template("template-1", "dashboard-1", true, DashboardTemplateJsonVersion.LATEST_VERSION.getValue());

    JsonNode result = new JsonDashboardTemplateMigrator(node).migrate();

    JsonNode dashboard = result.get("dashboard");
    assertThat(dashboard.has("dashboardDisplayType")).isFalse();
    assertThat(dashboard.get("isTopMenu").asBoolean()).isTrue();
  }

  @Test
  void migrate_ceilingVersion_stopsConverterChainAtGivenVersion() {
    JsonNode node = template("template-1", "dashboard-1", true, null);

    JsonNode result = new JsonDashboardTemplateMigrator(node, new DashboardTemplateJsonVersion("12.0.0")).migrate();

    JsonNode dashboard = result.get("dashboard");
    // isTopMenu -> dashboardDisplayType converter is registered at 13.1.0, above this ceiling.
    assertThat(dashboard.has("dashboardDisplayType")).isFalse();
    assertThat(dashboard.get("isTopMenu").asBoolean()).isTrue();
    assertThat(dashboard.get("version").asText()).isEqualTo("12.0.0");
  }

  @Test
  void migrate_wrapperShape_leavesTopMenuFlagsAlone_butStillNormalizesStateFilterValues() {
    ObjectNode wrapper = mapper.createObjectNode();
    wrapper.put("version", "1.0");
    ArrayNode items = wrapper.putArray("items");
    items.add(template("template-1", "dashboard-1", true, null));
    ObjectNode secondTemplate = template("template-2", "default-task-list-dashboard", null, null);
    taskWidgetWithStateFilterList((ObjectNode) secondTemplate.get("dashboard"), "PARKED", "RESUMED");
    items.add(secondTemplate);

    JsonNode result = new JsonDashboardTemplateMigrator(wrapper).migrate();

    JsonNode firstDashboard = result.get("items").get(0).get("dashboard");
    JsonNode secondDashboard = result.get("items").get(1).get("dashboard");
    assertThat(firstDashboard.has("dashboardDisplayType")).isFalse();
    assertThat(firstDashboard.get("isTopMenu").asBoolean()).isTrue();
    assertThat(secondDashboard.has("dashboardDisplayType")).isFalse();
    assertThat(secondDashboard.has("version")).isFalse();
    assertThat(result.get("version").asText()).isEqualTo("1.0");

    JsonNode filterList = secondDashboard.get("widgets").get(0).get("columns").get(0).get("filterList");
    assertThat(filterList).extracting(JsonNode::asText).containsExactlyInAnyOrder("OPEN", "IN_PROGRESS");
  }

  @Test
  void migrate_arrayShape_migratesEachElement() {
    ArrayNode array = mapper.createArrayNode();
    array.add(template("template-1", "dashboard-1", false, null));

    JsonNode result = new JsonDashboardTemplateMigrator(array).migrate();

    assertThat(result.get(0).get("dashboard").get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
  }

  @Test
  void migrate_templateWithoutDashboardNode_treatsTemplateItselfAsTheWidgetHoldingNode() {
    ObjectNode template = mapper.createObjectNode();
    template.put("id", "template-1");
    template.put("isTopMenu", true);

    JsonNode result = new JsonDashboardTemplateMigrator(template).migrate();

    assertThat(result.has("dashboard")).isFalse();
    assertThat(result.get("dashboardDisplayType").asText()).isEqualTo("top_menu");
    assertThat(result.get("version").asText()).isEqualTo(DashboardTemplateJsonVersion.LATEST_VERSION.getValue());
  }

  @Test
  void migrate_staleTaskStateInColumnFilterList_convertsToTaskBusinessState() {
    // 11.3.0 excludes both the v112 and v113 version-gated converters, isolating the safety net.
    JsonNode node = template("template-1", "dashboard-1", null, "11.3.0");
    taskWidgetWithStateFilterList((ObjectNode) node.get("dashboard"), "PARKED", "RESUMED", "SUSPENDED");

    JsonNode result = new JsonDashboardTemplateMigrator(node).migrate();

    JsonNode filterList =
        result.get("dashboard").get("widgets").get(0).get("columns").get(0).get("filterList");
    assertThat(filterList).extracting(JsonNode::asText).containsExactlyInAnyOrder("OPEN", "IN_PROGRESS");
  }

  @Test
  void migrate_staleTaskStateInWidgetFiltersArray_convertsToTaskBusinessState() {
    // No version-gated converter ever targets the "filters" array - only the safety net does.
    JsonNode node = template("template-1", "dashboard-1", null, DashboardTemplateJsonVersion.LATEST_VERSION.getValue());
    taskWidgetWithStateFilterValues((ObjectNode) node.get("dashboard"), "PARKED", "RESUMED");

    JsonNode result = new JsonDashboardTemplateMigrator(node).migrate();

    JsonNode values = result.get("dashboard").get("widgets").get(0).get("filters").get(0).get("values");
    assertThat(values).extracting(JsonNode::asText).containsExactlyInAnyOrder("OPEN", "IN_PROGRESS");
  }
}
