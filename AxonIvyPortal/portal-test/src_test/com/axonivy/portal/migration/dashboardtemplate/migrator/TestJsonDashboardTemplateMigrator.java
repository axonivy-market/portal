package com.axonivy.portal.migration.dashboardtemplate.migrator;

import static org.assertj.core.api.Assertions.assertThat;

import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * {@link JsonDashboardTemplateMigrator} delegates to the same converter chain used for plain
 * dashboards ({@code JsonDashboardConverterFactory}), but applied to the nested "dashboard" object of
 * a template rather than the template itself - and it reads/writes the migration version from that
 * same nested node. {@code @IvyTest} is required because {@code JsonDashboardTemplateMigrator} logs
 * via {@code Ivy.log()} while running converters.
 */
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
  void migrate_singleTemplate_alreadyAtLatestVersion_doesNotReRunConverters() {
    JsonNode node =
        template("template-1", "dashboard-1", true, DashboardTemplateJsonVersion.LATEST_VERSION.getValue());

    JsonNode result = new JsonDashboardTemplateMigrator(node).migrate();

    JsonNode dashboard = result.get("dashboard");
    // isTopMenu is only translated into dashboardDisplayType by the converter chain - if no
    // converter ran (because the dashboard is already stamped at the latest version), the raw flag
    // must still be there, untouched.
    assertThat(dashboard.has("dashboardDisplayType")).isFalse();
    assertThat(dashboard.get("isTopMenu").asBoolean()).isTrue();
  }

  @Test
  void migrate_ceilingVersion_stopsConverterChainAtGivenVersion() {
    JsonNode node = template("template-1", "dashboard-1", true, null);

    JsonNode result = new JsonDashboardTemplateMigrator(node, new DashboardTemplateJsonVersion("12.0.0")).migrate();

    JsonNode dashboard = result.get("dashboard");
    // The converter that turns "isTopMenu" into "dashboardDisplayType" is registered at 13.1.0,
    // above this 12.0.0 ceiling, so it must not run.
    assertThat(dashboard.has("dashboardDisplayType")).isFalse();
    assertThat(dashboard.get("isTopMenu").asBoolean()).isTrue();
    assertThat(dashboard.get("version").asText()).isEqualTo("12.0.0");
  }

  @Test
  void migrate_wrapperShape_isReturnedCompletelyUnchanged() {
    // Once a collection is wrapped, the wrapper's own version is the sole gate for this collection
    // format - per-item version is never read again, and items are NOT re-run through the per-item
    // converter chain, regardless of what their own nested "dashboard" node looks like. This is
    // required, not optional: per-item version is stripped once wrapped (see JsonListWrapper), so
    // without this short-circuit every read of already-current data would see an absent version,
    // fall back to OLDEST, and re-run every converter unconditionally forever.
    ObjectNode wrapper = mapper.createObjectNode();
    wrapper.put("version", "1.0");
    ArrayNode items = wrapper.putArray("items");
    items.add(template("template-1", "dashboard-1", true, null));
    items.add(template("template-2", "default-task-list-dashboard", null, null));

    JsonNode result = new JsonDashboardTemplateMigrator(wrapper).migrate();

    JsonNode firstDashboard = result.get("items").get(0).get("dashboard");
    JsonNode secondDashboard = result.get("items").get(1).get("dashboard");
    assertThat(firstDashboard.has("dashboardDisplayType")).isFalse();
    assertThat(firstDashboard.get("isTopMenu").asBoolean()).isTrue();
    assertThat(secondDashboard.has("dashboardDisplayType")).isFalse();
    assertThat(secondDashboard.has("version")).isFalse();
    // The wrapper-level "version" tracks the JSON collection format, not any one template's
    // migration version, so it must be left exactly as it was.
    assertThat(result.get("version").asText()).isEqualTo("1.0");
  }

  @Test
  void migrate_arrayShape_migratesEachElement() {
    ArrayNode array = mapper.createArrayNode();
    array.add(template("template-1", "dashboard-1", false, null));

    JsonNode result = new JsonDashboardTemplateMigrator(array).migrate();

    assertThat(result.get(0).get("dashboard").get("dashboardDisplayType").asText()).isEqualTo("sub_menu");
  }

  @Test
  void migrate_templateWithoutDashboardNode_doesNotThrow() {
    ObjectNode template = mapper.createObjectNode();
    template.put("id", "template-1");

    JsonNode result = new JsonDashboardTemplateMigrator(template).migrate();

    assertThat(result.has("dashboard")).isFalse();
  }
}
