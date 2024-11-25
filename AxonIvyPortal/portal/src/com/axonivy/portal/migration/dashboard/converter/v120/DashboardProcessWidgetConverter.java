package com.axonivy.portal.migration.dashboard.converter.v120;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class DashboardProcessWidgetConverter implements IJsonConverter {

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
    return new DashboardJsonVersion("12.0.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    (new JsonWidgetSearch(jsonNode)).findWidgets().forEach(widget -> {
        migrateOldProcessWidgetTypes((ObjectNode) widget);
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