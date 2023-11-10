package com.axonivy.portal.migration.common.visitor;

import java.util.Optional;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonDashboardVisitor {

  private final ObjectNode dashboard;
  private Consumer<JsonNode> visitor;

  public JsonDashboardVisitor(JsonNode dashboard) {
    this.dashboard = (ObjectNode) dashboard;
  }

  public void visitWidgets(Consumer<JsonNode> visitor) {
    this.visitor = visitor;
    visitWidgets(dashboard.get("widgets"));
  }

  private void visitWidgets(JsonNode node) {
    var array = Optional.ofNullable(node)
      .filter(JsonNode::isArray)
      .map(ArrayNode.class::cast);
    if (array.isEmpty()) {
      return;
    }
    array.get().iterator().forEachRemaining(this::visitWidget);
  }

  private void visitWidget(JsonNode widget) {
    var object = Optional.ofNullable(widget)
      .filter(JsonNode::isObject)
      .map(ObjectNode.class::cast);
    if (object.isEmpty()) {
      return;
    }

    if (visitor != null) {
      visitor.accept(widget);
    }
  }
}
