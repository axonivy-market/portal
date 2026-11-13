package ch.ivy.addon.portalkit.schema;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import com.axonivy.portal.dto.definition.PortalCustomStatistic;
import com.axonivy.portal.dto.definition.PortalDashboard;
import com.axonivy.portal.dto.definition.PortalDashboardTemplate;
import com.axonivy.portal.schema.generator.PortalSchemaGenerator;
import com.axonivy.portal.schema.module.PortalJacksonSchemaModule;
import com.fasterxml.jackson.annotation.JsonProperty;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

class TestPortalSchema {

  private static final String SCHEMA_DRAFT = "https://json-schema.org/draft/2020-12/schema";

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

  private static void assertSchemaDraft(ObjectNode schema) {
    assertThat(schema.get("$schema").asString()).isEqualTo(SCHEMA_DRAFT);
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
