package com.axonivy.portal.migration.casedetails.migrator;

import static org.assertj.core.api.Assertions.assertThat;

import com.axonivy.portal.bo.jsonversion.CaseDetailsJsonVersion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * {@code @IvyTest} is required because {@link JsonCaseDetailsMigrator} logs via {@code Ivy.log()}
 * while running converters.
 */
@IvyTest
class TestJsonCaseDetailsMigrator {

  private final ObjectMapper mapper = new ObjectMapper();

  private ObjectNode legacyConfiguration(String id) {
    ObjectNode configuration = mapper.createObjectNode();
    configuration.put("id", id);
    ArrayNode widgets = configuration.putArray("widgets");
    ObjectNode information = widgets.addObject();
    information.put("type", "information");
    information.put("id", "information");
    ObjectNode layout = information.putObject("layout");
    layout.put("x", 0);
    layout.put("y", 2);
    layout.put("w", 6);
    layout.put("h", 10);
    return configuration;
  }

  @Test
  void migrate_legacyBareObject_runsConverterChainAndStampsLatestVersion() {
    JsonNode node = legacyConfiguration("default-case-detail");

    JsonNode result = new JsonCaseDetailsMigrator(node).migrate();

    ArrayNode widgets = (ArrayNode) result.get("widgets");
    assertThat(widgets.get(0).get("type").asText()).isEqualTo("summary");
    assertThat(result.get("version").asText()).isEqualTo(CaseDetailsJsonVersion.LATEST_VERSION.getValue());
  }

  @Test
  void migrate_wrapperShape_isReturnedCompletelyUnchanged() {
    // Once wrapped, the wrapper's own version is the sole gate for this collection format -
    // per-item version is never read again, and items are NOT re-run through the per-item
    // converter chain. This is required, not optional: per-item version is stripped once wrapped
    // (see JsonListWrapper), so without this short-circuit every read of already-current data
    // would see an absent version, fall back to OLDEST, and re-run CaseDetailsWidgetConverter
    // unconditionally forever - and that converter is not idempotent on its own (see
    // TestCaseDetailsWidgetConverter).
    ObjectNode wrapper = mapper.createObjectNode();
    wrapper.put("version", "14.0.0");
    ArrayNode items = wrapper.putArray("items");
    items.add(legacyConfiguration("default-case-detail"));

    JsonNode result = new JsonCaseDetailsMigrator(wrapper).migrate();

    JsonNode configuration = result.get("items").get(0);
    ArrayNode widgets = (ArrayNode) configuration.get("widgets");
    assertThat(widgets.get(0).get("type").asText()).isEqualTo("information");
    assertThat(configuration.has("version")).isFalse();
  }
}
 Required, not optional: per-item version is
      // stripped once wrapped, so without this short-circuit every read would fall back to
      // OLDEST and unconditionally re-run every converter on already-current data forever.