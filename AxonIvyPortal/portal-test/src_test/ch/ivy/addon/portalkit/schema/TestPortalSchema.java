package ch.ivy.addon.portalkit.schema;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.definition.PortalCustomStatistic;
import com.axonivy.portal.dto.definition.PortalDashboard;
import com.axonivy.portal.dto.definition.PortalDashboardTemplate;
import com.axonivy.portal.schema.generator.PortalSchemaGenerator;
import com.axonivy.portal.schema.module.PortalJacksonSchemaModule;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

class TestPortalSchema {

  private static final String SCHEMA_DRAFT = "https://json-schema.org/draft/2020-12/schema";

  private final PortalSchemaGenerator schemaGenerator = new PortalSchemaGenerator();

  @Test
  void dashboardSchema_hasDashboardItems() {
    ObjectNode schema = schemaGenerator.generateSchema(PortalDashboard.class);
    assertSchemaDraft(schema);

    assertItemDefinition(schema, "Dashboard");
  }

  @Test
  void statisticSchema_hasStatisticItems() {
    ObjectNode schema = schemaGenerator.generateSchema(PortalCustomStatistic.class);
    assertSchemaDraft(schema);

    assertItemDefinition(schema, "Statistic");
  }

  @Test
  void excludedPropertiesAreOmitted() {
    PortalJacksonSchemaModule.EXCLUDED_PROPERTIES.forEach((type, properties) -> {
      ObjectNode schema = schemaGenerator.generateSchema(type);
      assertPropertiesOmitted(schema.path("properties"), type, properties);
    });
  }

  @Test
  void dashboardTemplateSchema_referencesDashboardSchema() {
    ObjectNode schema = schemaGenerator.generateSchema(PortalDashboardTemplate.class);
    assertSchemaDraft(schema);

    JsonNode templateProperties = assertItemDefinition(schema, "DashboardTemplate");
    assertThat(templateProperties.path("dashboard").path("$ref").asString())
        .endsWith("dashboard-schema.json");
  }

  private static void assertSchemaDraft(ObjectNode schema) {
    assertThat(schema.get("$schema").asString()).isEqualTo(SCHEMA_DRAFT);
  }

  private static JsonNode assertItemDefinition(ObjectNode schema, String typeName) {
    JsonNode itemSchema = schema.path("properties").path("items").path("items");
    assertThat(itemSchema.path("$ref").asString()).as("items reference").endsWith("/" + typeName);

    JsonNode properties = schema.path("$defs").path(typeName).path("properties");
    assertThat(properties.isObject()).as("properties for %s", typeName).isTrue();
    return properties;
  }

  private static void assertPropertiesOmitted(JsonNode schemaProperties, Class<?> type, Set<String> names) {
    names.forEach(name -> assertThat(schemaProperties.has(name))
        .as("schema property '%s' should be omitted for %s", name, type.getSimpleName())
        .isFalse());
  }
}
