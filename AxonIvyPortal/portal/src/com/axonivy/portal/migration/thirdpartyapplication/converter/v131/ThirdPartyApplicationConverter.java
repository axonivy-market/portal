package com.axonivy.portal.migration.thirdpartyapplication.converter.v131;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.ApplicationJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Converter for ThirdPartyApplication configuration from v12.x to v13.1
 * 
 * This converter handles the migration of third-party application configurations
 * to ensure compatibility with version 13.1 structural changes and improvements.
 * 
 * Key migration tasks:
 * 1. Ensures all required fields have proper default values
 * 2. Maintains backward compatibility for display name structures
 * 3. Validates and fixes permission array structures
 * 4. Sets proper isPublic flag for all applications
 * 
 * Migration scenarios handled:
 * - Missing menuOrdinal: defaults to 0
 * - Missing or null menuIcon: defaults to "si si-software-layout-header-3columns"
 * - Missing name field: uses displayName as fallback
 * - Simple string permissions: converts to array format
 * - Empty permissions: adds default "Everybody" permission
 * - Missing isPublic flag: defaults to true
 * 
 * This converter is part of the refactoring that introduced the dedicated
 * ThirdPartyApplicationBean for better separation of concerns.
 * 
 * @since 13.1.0
 */
public class ThirdPartyApplicationConverter implements IJsonConverter {

  private static final String DISPLAY_NAME = "displayName";
  private static final String NAME = "name";
  private static final String MENU_ICON = "menuIcon";
  private static final String MENU_ORDINAL = "menuOrdinal";
  private static final String LINK = "link";
  private static final String PERMISSIONS = "permissions";
  private static final String IS_PUBLIC = "isPublic";

  @Override
  public AbstractJsonVersion version() {
    return new ApplicationJsonVersion("13.1.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    if (jsonNode.isArray()) {
      jsonNode.elements().forEachRemaining(this::convertApplication);
    } else {
      convertApplication(jsonNode);
    }
  }

  private void convertApplication(JsonNode applicationNode) {
    ObjectNode appObject = (ObjectNode) applicationNode;
    
    // Ensure all required fields exist with proper defaults
    ensureRequiredFields(appObject);
    
    // Handle display name structure migration if needed
    migrateDisplayNameStructure(appObject);
    
    // Ensure permissions structure is correct
    ensurePermissionsStructure(appObject);
    
    // Ensure proper isPublic flag
    ensurePublicFlag(appObject);
  }
  /**
   * Ensures all required fields exist with proper default values
   * 
   * Example transformations:
   * Before: {"displayName": "My App", "link": "http://example.com"}
   * After:  {"displayName": "My App", "name": "My App", "link": "http://example.com", 
   *          "menuOrdinal": 0, "menuIcon": "si si-software-layout-header-3columns"}
   */
  private void ensureRequiredFields(ObjectNode appObject) {
    // Ensure menuOrdinal exists
    if (!appObject.has(MENU_ORDINAL)) {
      appObject.put(MENU_ORDINAL, 0);
    }
    
    // Ensure menuIcon has a default value
    if (!appObject.has(MENU_ICON) || appObject.get(MENU_ICON).isNull()) {
      appObject.put(MENU_ICON, "si si-software-layout-header-3columns");
    }
    
    // Ensure name field exists - use displayName as fallback
    if (!appObject.has(NAME) || appObject.get(NAME).asText().trim().isEmpty()) {
      JsonNode displayNameNode = appObject.get(DISPLAY_NAME);
      if (displayNameNode != null && !displayNameNode.asText().trim().isEmpty()) {
        appObject.put(NAME, displayNameNode.asText());
      }
    }
  }
  /**
   * Migrates display name structure to support multi-language if needed
   * 
   * Example transformations:
   * Before: {"displayName": "Simple App"}
   * After:  {"displayName": "Simple App", "name": "Simple App"}
   * 
   * Before: {"displayName": "{\"en\":\"App\",\"de\":\"Anwendung\"}"}
   * After:  {"displayName": "{\"en\":\"App\",\"de\":\"Anwendung\"}", "name": "{\"en\":\"App\",\"de\":\"Anwendung\"}"}
   */
  private void migrateDisplayNameStructure(ObjectNode appObject) {
    JsonNode displayNameNode = appObject.get(DISPLAY_NAME);
    
    if (displayNameNode != null && displayNameNode.isTextual()) {
      String displayName = displayNameNode.asText().trim();
      
      // Check if it's a JSON string (multi-language format)
      if (isJsonString(displayName)) {
        // Keep the JSON structure for multi-language support
        appObject.put(DISPLAY_NAME, displayName);
        
        // Ensure name field matches displayName for multi-language apps
        if (!appObject.has(NAME) || !appObject.get(NAME).asText().equals(displayName)) {
          appObject.put(NAME, displayName);
        }
      } else {
        // Simple string displayName
        if (!displayName.isEmpty()) {
          appObject.put(DISPLAY_NAME, displayName);
          
          // Set name field if it doesn't exist or is empty
          if (!appObject.has(NAME) || appObject.get(NAME).asText().trim().isEmpty()) {
            appObject.put(NAME, displayName);
          }
        }
      }
    }
  }

  /**
   * Checks if a string represents a JSON object (starts with { and ends with })
   */
  private boolean isJsonString(String value) {
    if (value == null || value.trim().length() < 2) {
      return false;
    }
    String trimmed = value.trim();
    return trimmed.startsWith("{") && trimmed.endsWith("}");
  }
  /**
   * Ensures permissions array structure is correct
   * 
   * Example transformations:
   * Before: {"permissions": "Admin"}  →  After: {"permissions": ["Admin"]}
   * Before: {"permissions": []}       →  After: {"permissions": ["Everybody"]}
   * Before: {}                        →  After: {"permissions": ["Everybody"]}
   */
  private void ensurePermissionsStructure(ObjectNode appObject) {
    JsonNode permissionsNode = appObject.get(PERMISSIONS);
    
    if (permissionsNode == null) {
      // Add default permissions: ["Everybody"]
      ArrayNode permissionsArray = appObject.arrayNode();
      permissionsArray.add("Everybody");
      appObject.set(PERMISSIONS, permissionsArray);
    } else if (permissionsNode.isTextual()) {
      // Convert single permission string to array
      ArrayNode permissionsArray = appObject.arrayNode();
      String permission = permissionsNode.asText();
      if (permission != null && !permission.trim().isEmpty()) {
        permissionsArray.add(permission.trim());
      } else {
        permissionsArray.add("Everybody");
      }
      appObject.set(PERMISSIONS, permissionsArray);
    } else if (permissionsNode.isArray() && permissionsNode.size() == 0) {
      // If empty array, add default permission
      ArrayNode permissionsArray = (ArrayNode) permissionsNode;
      permissionsArray.add("Everybody");
    }
  }

  /**
   * Ensures the isPublic flag is properly set
   */
  private void ensurePublicFlag(ObjectNode appObject) {
    if (!appObject.has(IS_PUBLIC)) {
      appObject.put(IS_PUBLIC, true);
    }
  }
}
