package ch.ivy.addon.portalkit.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;



public class DashboardMigrationUtils {

  private static final String WIDGETS_NODE = "widgets";
  private static final String TYPE = "type";
  private static final String PROCESS = "process";
  private static final String DISPLAY_MODE = "displayMode";

  private static enum ProcessType {
    COMPACT_MODE("COMPACT_MODE", "compact-process"),
    COMBINED_MODE("COMBINED_MODE", "combined-process"),
    FULL_MODE("FULL_MODE", "full-process"),
    IMAGE_MODE("IMAGE_MODE", "image-process");

    private ProcessType(String oldType, String newType) {
      this.oldType = oldType;
      this.newType = newType;
    }

    private String oldType;
    private String newType;

    public static ProcessType findByOldType(String value) {
      for (ProcessType type : ProcessType.values()) {
        if (type.oldType.contentEquals(value)) {
          return type;
        }
      }
      return null;
    }

    public String getNewType() {
      return newType;
    }
  }

  public static String migrateOldDashboards(String dashboardJson)
      throws JsonMappingException, JsonProcessingException {
    JsonNode dashboardsNode = BusinessEntityConverter.getObjectMapper()
        .readTree(dashboardJson);
    dashboardsNode.forEach(dashboard -> {
      JsonNode widgetsNode = dashboard.get(WIDGETS_NODE);
      if (widgetsNode != null) {
        widgetsNode.forEach(widget -> {
          migrateOldProcessWidgetTypes((ObjectNode) widget);
        });
      }
    });

    return dashboardsNode.toString();
  }

  /**
   * Migration for old process widget types
   * 
   * @param widgetNode
   */
  private static void migrateOldProcessWidgetTypes(ObjectNode widgetNode) {
    if (widgetNode.get(TYPE).asText().contentEquals(PROCESS)) {
      ProcessType displayMode = ProcessType
          .findByOldType(widgetNode.get(DISPLAY_MODE).asText());
      if (displayMode != null) {
        widgetNode.set(TYPE, new TextNode(displayMode.getNewType()));
      }
    }
  }
}
