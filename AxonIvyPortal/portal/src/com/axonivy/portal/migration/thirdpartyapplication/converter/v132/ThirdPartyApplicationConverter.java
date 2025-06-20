package com.axonivy.portal.migration.thirdpartyapplication.converter.v132;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.ApplicationJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ThirdPartyApplicationConverter implements IJsonConverter {

  @Override
  public AbstractJsonVersion version() {
    return new ApplicationJsonVersion("13.2.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    if (jsonNode.isArray()) {
      jsonNode.elements().forEachRemaining(this::addDefaultPermissions);
    } else {
      addDefaultPermissions(jsonNode);
    }
  }

  private void addDefaultPermissions(JsonNode applicationNode) {
    ObjectNode appObject = (ObjectNode) applicationNode;
    
    // Check if permissions field already exists
    if (!appObject.has("permissions")) {
      // Add default permissions: ["Everybody"]
      ArrayNode permissionsArray = appObject.arrayNode();
      permissionsArray.add("Everybody");
      appObject.set("permissions", permissionsArray);
    }
  }
}

