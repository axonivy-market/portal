package com.axonivy.portal.migration.common.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.axonivy.portal.migration.common.visitor.JsonDashboardVisitor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonWidgetSearch {
  private final JsonNode dashboard;

  private Predicate<JsonNode> filter = e -> true;

  public JsonWidgetSearch(JsonNode dashboard) {
    this.dashboard = dashboard;
  }

  public JsonWidgetSearch type(String type) {
    return filter(JCondition.isType(type));
  }

  private JsonWidgetSearch filter(Predicate<JsonNode> elementFilter) {
    if (elementFilter != null) {
      filter = filter.and(elementFilter);
      return this;
    }
    return null;
  }

  private List<JsonNode> findWidgets() {
    List<JsonNode> matches = new ArrayList<>();
    new JsonDashboardVisitor(dashboard).visitWidgets(element -> {
      if (filter.test(element)) {
        matches.add(element);
      }
    });
    return matches;
  }

  public Stream<ArrayNode> findColumns() {
    return findWidgets().stream()
      .map(n -> columnsOf(n).orElse(null))
      .filter(Objects::nonNull);
  }

  private static Optional<ArrayNode> columnsOf(JsonNode widget) {
    return Optional.ofNullable(widget.get("columns"))
      .filter(JsonNode::isArray)
      .map(ArrayNode.class::cast);
  }
}
