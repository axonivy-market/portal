package com.axonivy.portal.userexamples.utils;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import ch.ivyteam.ivy.environment.Ivy;

public class JsonUtils {

  public static String mergeJsonArrays(String existingJson, String newJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode existingArray = mapper.readTree(existingJson);
      JsonNode newArray = mapper.readTree(newJson);

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
}
