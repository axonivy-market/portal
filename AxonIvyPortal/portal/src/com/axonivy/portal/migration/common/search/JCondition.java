package com.axonivy.portal.migration.common.search;

import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonNode;

public class JCondition {

  public static Predicate<JsonNode> isType(String type) {
    return hasTextFieldValueEqualsIgnoreCase("type", type);
  }

  public static Predicate<JsonNode> isWidgetType(String type) {
    return hasTextFieldValueEqualsIgnoreCase("widgetType", type);
  }

  public static Predicate<JsonNode> isField(String field) {
    return hasTextFieldValueEquals("field", field);
  } 

  public static Predicate<JsonNode> hasTextFieldValueEquals(String field, String id) {
    return element -> Optional.ofNullable(element.get(field))
            .filter(JsonNode::isTextual)
            .map(JsonNode::asText)
            .filter(eId -> id.equals(eId))
            .isPresent();
  }

  public static Predicate<JsonNode> hasTextFieldValueEqualsIgnoreCase(String field, String id) {
    return element -> Optional.ofNullable(element.get(field))
            .filter(JsonNode::isTextual)
            .map(JsonNode::asText)
            .filter(eId -> id.equalsIgnoreCase(eId))
            .isPresent();
  }
}