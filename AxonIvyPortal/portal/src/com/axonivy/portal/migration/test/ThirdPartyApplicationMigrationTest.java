package com.axonivy.portal.migration.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.axonivy.portal.migration.thirdpartyapplication.migrator.JsonThirdPartyApplicationMigrator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple test class to verify the third-party application migration works correctly
 */
public class ThirdPartyApplicationMigrationTest {
    
    public static void main(String[] args) {
        try {
            testMigration();
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void testMigration() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        // Read test JSON file
        String jsonContent = Files.readString(Paths.get("c:\\Work\\Portal\\13\\portal\\AxonIvyPortal\\portal\\test_migration.json"));
        JsonNode originalNode = mapper.readTree(jsonContent);
        
        System.out.println("=== ORIGINAL JSON ===");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(originalNode));
        
        // Test application without permissions field
        JsonNode app1 = originalNode.get(0);
        System.out.println("\\nApp1 has permissions field: " + app1.has("permissions"));
        
        // Test application with permissions field
        JsonNode app2 = originalNode.get(1);
        System.out.println("App2 has permissions field: " + app2.has("permissions"));
        if (app2.has("permissions")) {
            System.out.println("App2 permissions: " + app2.get("permissions"));
        }
        
        // Run migration
        JsonThirdPartyApplicationMigrator migrator = new JsonThirdPartyApplicationMigrator(originalNode);
        JsonNode migratedNode = migrator.migrate();
        
        System.out.println("\\n=== MIGRATED JSON ===");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(migratedNode));
        
        // Verify migration results
        JsonNode migratedApp1 = migratedNode.get(0);
        JsonNode migratedApp2 = migratedNode.get(1);
        
        System.out.println("\\n=== MIGRATION VERIFICATION ===");
        
        // Check if app1 now has permissions field
        if (migratedApp1.has("permissions")) {
            JsonNode permissions = migratedApp1.get("permissions");
            if (permissions.isArray() && permissions.size() == 1 && "Everybody".equals(permissions.get(0).asText())) {
                System.out.println("✓ App1 migration SUCCESS: Added default permissions [\"Everybody\"]");
            } else {
                System.out.println("✗ App1 migration FAILED: Incorrect permissions value");
            }
        } else {
            System.out.println("✗ App1 migration FAILED: No permissions field added");
        }
        
        // Check if app2 permissions were preserved
        if (migratedApp2.has("permissions")) {
            JsonNode permissions = migratedApp2.get("permissions");
            if (permissions.isArray() && permissions.size() == 2 && 
                "UiDesigner".equals(permissions.get(0).asText()) && 
                "BackendDeveloper".equals(permissions.get(1).asText())) {
                System.out.println("✓ App2 migration SUCCESS: Existing permissions preserved");
            } else {
                System.out.println("✗ App2 migration FAILED: Existing permissions were modified");
            }
        } else {
            System.out.println("✗ App2 migration FAILED: Existing permissions field was removed");
        }
        
        // Check version field
        if (migratedApp1.has("version") && migratedApp2.has("version")) {
            String version1 = migratedApp1.get("version").asText();
            String version2 = migratedApp2.get("version").asText();
            if ("13.1.0".equals(version1) && "13.1.0".equals(version2)) {
                System.out.println("✓ Version update SUCCESS: Both applications updated to version 13.1.0");
            } else {
                System.out.println("✗ Version update FAILED: Version not set correctly (app1: " + version1 + ", app2: " + version2 + ")");
            }
        } else {
            System.out.println("✗ Version update FAILED: Version field not added");
        }
        
        System.out.println("\\n=== TEST COMPLETED ===");
    }
}
