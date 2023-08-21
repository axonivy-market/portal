package com.axonivy.portal.migration.common.search;

import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonDashboardConfigurationSearch {
  private final JsonNode configuration;

  private Predicate<JsonNode> filter = e -> true;

  public JsonDashboardConfigurationSearch(JsonNode configuration) {
    this.configuration = configuration;
  }

  public JsonDashboardConfigurationSearch type(String type) {
    return filter(JCondition.isWidgetType(type));
  }

  private JsonDashboardConfigurationSearch filter(Predicate<JsonNode> elementFilter) {
    if (elementFilter != null) {
      filter = filter.and(elementFilter);
      return this;
    }
    return null;
  }

  private JsonNode findConfig() {
    if (filter.test(configuration)) {
      return configuration;
    }
    return null;
  }

  public Optional<ArrayNode> findFilterableColumns() {
    return Optional.ofNullable(findConfig())
        .map(config -> config.get("filterableColumns"))
        .filter(JsonNode::isArray)
        .map(ArrayNode.class::cast);
  }

}
