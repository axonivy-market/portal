package ch.ivy.addon.portalkit.schema;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

import com.axonivy.portal.dto.jsondefinition.PortalCustomStatistic;
import com.axonivy.portal.dto.jsondefinition.PortalDashboard;
import com.axonivy.portal.dto.jsondefinition.PortalDashboardTemplate;
import com.axonivy.portal.schema.generator.PortalSchemaGenerator;
import com.axonivy.portal.schema.module.PortalJacksonSchemaModule;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.victools.jsonschema.generator.SchemaVersion;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

class TestPortalSchema {

  private final PortalSchemaGenerator schemaGenerator = new PortalSchemaGenerator();

  @Test
  void rootSchema_hasSchemaVersionAndItemsProperties() {
    for (Class<?> definition : List.of(PortalDashboard.class, PortalCustomStatistic.class, PortalDashboardTemplate.class)) {
      ObjectNode schema = schemaGenerator.generateSchema(definition);

      assertThat(schema.path("properties").propertyNames())
          .as("root properties of %s", definition.getSimpleName())
          .containsExactlyInAnyOrder("$schema", "version", "items");
    }
  }

  @Test
  void generatedSchema_hasCorrectSchemaDraft() {
    ObjectNode schema = schemaGenerator.generateSchema(PortalDashboard.class);
    assertSchemaDraft(schema);
  }

  @Test
  void excludedPropertiesExistOnClass() throws IntrospectionException {
    for (Entry<Class<?>, Set<String>> entry : PortalJacksonSchemaModule.EXCLUDED_PROPERTIES.entrySet()) {
      assertThat(beanPropertyNames(entry.getKey()))
          .as("bean properties of %s", entry.getKey().getSimpleName())
          .containsAll(entry.getValue());
    }
  }

  @Test
  void dashboardSchema_omitsExcludedColumnProperties() throws IntrospectionException {
    JsonNode definitions = schemaGenerator.generateSchema(PortalDashboard.class).path("$defs");
    for (String widgetType : List.of("TaskDashboardWidget", "CaseDashboardWidget")) {
      JsonNode columnProperties = definitions.path(widgetType)
          .path("properties").path("columns").path("items").path("properties");
      assertThat(columnProperties.isObject())
          .as("column properties of %s in the PortalDashboard schema", widgetType)
          .isTrue();
      for (String javaName : PortalJacksonSchemaModule.EXCLUDED_PROPERTIES.get(ColumnModel.class)) {
        String jsonName = jsonPropertyName(ColumnModel.class, javaName);
        assertThat(columnProperties.has(jsonName))
            .as("schema property '%s' (Java field '%s' of %s) should be omitted", jsonName, javaName, widgetType)
            .isFalse();
      }
    }
  }

  @Test
  void generatedSchema_excludedPropertiesAreOmitted() throws IntrospectionException {
    for (Entry<Class<?>, Set<String>> entry : PortalJacksonSchemaModule.EXCLUDED_PROPERTIES.entrySet()) {
      Class<?> type = entry.getKey();
      ObjectNode schema = schemaGenerator.generateSchema(type);
      for (String javaName : entry.getValue()) {
        String jsonName = jsonPropertyName(type, javaName);
        assertThat(schema.path("properties").has(jsonName))
            .as("schema property '%s' (Java field '%s' of %s) should be omitted", jsonName, javaName, type.getSimpleName())
            .isFalse();
      }
    }
  }

  @Test
  void rootSchema_requiresSchemaVersionAndItems() {
    for (Class<?> definition : List.of(PortalDashboard.class, PortalCustomStatistic.class, PortalDashboardTemplate.class)) {
      ObjectNode schema = schemaGenerator.generateSchema(definition);

      assertThat(requiredNames(schema))
          .as("required root properties of %s", definition.getSimpleName())
          .containsExactlyInAnyOrder("$schema", "version", "items");
    }
  }

  @Test
  void statisticAggregationType_allowsOnlyStandardAndCustom() {
    ObjectNode schema = schemaGenerator.generateSchema(PortalCustomStatistic.class);
    JsonNode statisticAggregation = schema.path("properties").path("items").path("items")
        .path("properties").path("statisticAggregation");
    assertThat(statisticAggregation.isObject())
        .as("statisticAggregation in the PortalCustomStatistic schema")
        .isTrue();

    JsonNode typeSchema = statisticAggregation.path("properties").path("type");
    assertThat(enumValues(typeSchema))
        .as("allowed values of statistic aggregation 'type'")
        .containsExactlyInAnyOrder("standard", "custom");
  }

  private static Set<String> requiredNames(ObjectNode schema) {
    Set<String> names = new HashSet<>();
    for (JsonNode name : schema.path("required")) {
      names.add(name.asString());
    }
    return names;
  }

  private static Set<String> enumValues(JsonNode propertySchema) {
    Set<String> values = new HashSet<>();
    for (JsonNode value : propertySchema.path("enum")) {
      values.add(value.asString());
    }
    return values;
  }

  private static void assertSchemaDraft(ObjectNode schema) {
    assertThat(schema.get("$schema").asString()).isEqualTo(SchemaVersion.DRAFT_2020_12.getIdentifier());
  }

  private static Set<String> beanPropertyNames(Class<?> type) throws IntrospectionException {
    return Arrays.stream(Introspector.getBeanInfo(type).getPropertyDescriptors())
        .map(PropertyDescriptor::getName)
        .collect(Collectors.toSet());
  }

  /** JSON name Jackson uses: @JsonProperty value on the getter or field if set, otherwise the Java name. */
  private static String jsonPropertyName(Class<?> type, String javaName) throws IntrospectionException {
    Method getter = Arrays.stream(Introspector.getBeanInfo(type).getPropertyDescriptors())
        .filter(descriptor -> descriptor.getName().equals(javaName))
        .map(PropertyDescriptor::getReadMethod)
        .filter(Objects::nonNull)
        .findFirst()
        .orElse(null);
    String name = explicitName(getter == null ? null : getter.getAnnotation(JsonProperty.class));
    if (name == null) {
      Field field = findField(type, javaName);
      name = explicitName(field == null ? null : field.getAnnotation(JsonProperty.class));
    }
    return name == null ? javaName : name;
  }

  private static String explicitName(JsonProperty annotation) {
    return annotation == null || annotation.value().isEmpty() ? null : annotation.value();
  }

  private static Field findField(Class<?> type, String name) {
    for (Class<?> current = type; current != null; current = current.getSuperclass()) {
      try {
        return current.getDeclaredField(name);
      } catch (NoSuchFieldException ex) {
        // continue with the superclass
      }
    }
    return null;
  }
}
