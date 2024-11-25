package com.axonivy.portal.migration.dashboardtemplate.converter.v120;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class DashboardTemplateProcessWidgetConverter implements IJsonConverter {
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

  @Override
  public AbstractJsonVersion version() {
    return new DashboardTemplateJsonVersion("12.0.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    jsonNode.forEach(dashboard -> {
      JsonNode widgetsNode = dashboard.get(WIDGETS_NODE);
      if (widgetsNode != null) {
        widgetsNode.forEach(widget -> {
          migrateOldProcessWidgetTypes((ObjectNode) widget);
        });
      }
    });
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
