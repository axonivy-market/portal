package com.axonivy.portal.userexamples.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.axonivy.portal.userexamples.dto.JsonListWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonUtils {

  private static final String DEFAULT_VERSION = "14.0.0";

  public static String mergeJsonArrays(String existingJson, String newJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode existingArray = asArrayNode(mapper, existingJson);
      JsonNode newArray = asArrayNode(mapper, newJson);
      String version = resolveVersion(mapper, existingJson, newJson);

      Set<String> existingIds = new HashSet<>();
      ArrayNode items = mapper.createArrayNode();

      existingArray.forEach(node -> {
        JsonNode idNode = node.get("id");
        if (idNode != null) {
          existingIds.add(idNode.asText());
        }
        items.add(node);
      });

      newArray.forEach(node -> {
        JsonNode idNode = node.get("id");
        if (idNode != null && !existingIds.contains(idNode.asText())) {
          existingIds.add(idNode.asText());
          items.add(node);
        }
      });

      ObjectNode result = mapper.createObjectNode();
      result.put(JsonListWrapper.VERSION_FIELD_NAME, version);
      result.set(JsonListWrapper.ITEMS_FIELD_NAME, items);
      return mapper.writeValueAsString(result);
    } catch (Exception e) {
      Ivy.log().error("Failed to merge JSON arrays", e);
      return existingJson;
    }
  }

  /**
   * Prefers a "version" already present on the incoming payload, falls back
   * to the existing payload's version, then to DEFAULT_VERSION if neither
   * side is wrapped yet.
   */
  private static String resolveVersion(ObjectMapper mapper, String existingJson, String newJson)
      throws JsonProcessingException {
    String fromNew = readVersion(mapper, newJson);
    if (fromNew != null) {
      return fromNew;
    }
    String fromExisting = readVersion(mapper, existingJson);
    return fromExisting != null ? fromExisting : DEFAULT_VERSION;
  }

  private static String readVersion(ObjectMapper mapper, String json) throws JsonProcessingException {
    JsonNode root = mapper.readTree(json);
    if (root.isObject() && root.has(JsonListWrapper.VERSION_FIELD_NAME)) {
      JsonNode version = root.get(JsonListWrapper.VERSION_FIELD_NAME);
      return version.isTextual() ? version.asText() : null;
    }
    return null;
  }

  /**
   * Accepts either a root-level JSON array, or a root-level JSON object that
   * wraps a single array field - the canonical {@code "items"} field per
   * {@link JsonListWrapper}, or (for backward compatibility with
   * not-yet-migrated data) any other single array field, e.g. {"dashboard": [...]}.
   */
  private static JsonNode asArrayNode(ObjectMapper mapper, String json) throws JsonProcessingException {
    JsonNode root = mapper.readTree(json);
    if (root.isArray()) {
      return root;
    }
    if (root.isObject()) {
      if (root.has(JsonListWrapper.ITEMS_FIELD_NAME) && root.get(JsonListWrapper.ITEMS_FIELD_NAME).isArray()) {
        return root.get(JsonListWrapper.ITEMS_FIELD_NAME);
      }
      if (root.isEmpty()) {
        return mapper.createArrayNode();
      }
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