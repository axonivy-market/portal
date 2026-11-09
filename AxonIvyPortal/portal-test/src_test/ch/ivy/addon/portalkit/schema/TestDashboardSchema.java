package ch.ivy.addon.portalkit.schema;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.jsonschema.generator.PortalSchemaGenerator;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

class TestDashboardSchema {

  private static final Set<String> EXCLUDED_COLUMN_PROPERTIES =
      Set.of("filter", "filterFrom", "filterList", "filterTo");

  private final PortalSchemaGenerator schemaGenerator = new PortalSchemaGenerator();
  private final String SCHEMA_DRAFT = "https://json-schema.org/draft/2020-12/schema";
  @Test
  void dashboardSchema_omitsExcludedProperties() {
    ObjectNode schema = schemaGenerator.generateSchema(Dashboard.class);
    assertThat(schema.get("$schema").asString()).isEqualTo(SCHEMA_DRAFT);
    
    JsonNode definitions = schema.path("$defs");

    assertPropertiesOmitted(schema.path("properties"), "oldId");
    assertPropertiesOmitted(definitions.path("WidgetLayout").path("properties"), "id");

    for (String widgetType : List.of("CaseDashboardWidget", "TaskDashboardWidget")) {
      JsonNode columnProperties = definitions.path(widgetType)
          .path("properties").path("columns").path("items").path("properties");
      assertThat(columnProperties.isObject()).as("column properties for %s", widgetType).isTrue();
      assertPropertiesOmitted(columnProperties, EXCLUDED_COLUMN_PROPERTIES);
    }
  }

  @Test
  void statisticSchema_omitsExcludedProperties() {
    ObjectNode schema = schemaGenerator.generateSchema(Statistic.class);

    assertPropertiesOmitted(schema.path("properties"), "canDrillDown", "additionalConfigs");
  }

  private static void assertPropertiesOmitted(JsonNode properties, String... names) {
    for (String name : names) {
      assertThat(properties.has(name)).as("schema property '%s' should be omitted", name).isFalse();
    }
  }

  private static void assertPropertiesOmitted(JsonNode properties, Set<String> names) {
    names.forEach(name -> assertThat(properties.has(name))
        .as("schema property '%s' should be omitted", name)
        .isFalse());
  }
}
