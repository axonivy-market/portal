package com.axonivy.portal.userexamples.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonUtils {

  public static String mergeJsonArrays(String existingJson, String newJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode existingArray = asArrayNode(mapper, existingJson);
      JsonNode newArray = asArrayNode(mapper, newJson);

      Set<String> existingIds = new HashSet<>();
      ArrayNode result = mapper.createArrayNode();

      existingArray.forEach(node -> {
        JsonNode idNode = node.get("id");
        if (idNode != null) {
          existingIds.add(idNode.asText());
        }
        result.add(node);
      });

      newArray.forEach(node -> {
        JsonNode idNode = node.get("id");
        if (idNode != null && !existingIds.contains(idNode.asText())) {
          existingIds.add(idNode.asText());
          result.add(node);
        }
      });

      return mapper.writeValueAsString(result);
    } catch (Exception e) {
      Ivy.log().error("Failed to merge JSON arrays", e);
      return existingJson;
    }
  }

  /**
   * Accepts either a root-level JSON array, or a root-level JSON object that
   * wraps a single array field (e.g. {"dashboard": [...]}). Returns the
   * underlying ArrayNode either way, so callers don't need to know the
   * wrapper's field name.
   */
  private static JsonNode asArrayNode(ObjectMapper mapper, String json) throws JsonProcessingException {
    JsonNode root = mapper.readTree(json);
    if (root.isArray()) {
      return root;
    }
    if (root.isObject()) {
      Iterator<JsonNode> fields = root.elements();
      while (fields.hasNext()) {
        JsonNode value = fields.next();
        if (value.isArray()) {
          return value;
        }
      }
    }
    throw new IllegalArgumentException("Expected a JSON array or an object wrapping one array field, got: " + json);
  }
}