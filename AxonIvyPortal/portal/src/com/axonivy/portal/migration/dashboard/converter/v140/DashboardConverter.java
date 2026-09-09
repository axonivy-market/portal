package com.axonivy.portal.migration.dashboard.converter.v140;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;

/**
 * Same migration logic as {@link com.axonivy.portal.migration.dashboard.converter.v131.DashboardConverter},
 * re-registered at 14.0.0 so that dashboards gate through an explicit converter for this version
 * instead of relying only on {@link com.axonivy.portal.migration.dashboard.migrator.JsonDashboardMigrator}'s
 * {@code updateVersion()} to bump them to the latest version. Running the same idempotent checks again
 * for dashboards already migrated at 13.1.0 is harmless - every branch here only fills in a value that
 * is still missing, it never overwrites data that's already correct.
 */
public class DashboardConverter implements IJsonConverter {

  private static final String SELECTED_DASHBOARD_DISPLAY_TYPE = "dashboardDisplayType";
  private static final String IS_TOP_MENU = "isTopMenu";
  private static final String PIN = "pin";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("14.0.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {

    ObjectNode objectNode = (ObjectNode) jsonNode;
    String dashboardId = objectNode.path("id").asText();
    boolean isDefaultTaskOrCaseList =
        "default-task-list-dashboard".equals(dashboardId) || "default-case-list-dashboard".equals(dashboardId);

    JsonNode isTopMenuNode = objectNode.get(IS_TOP_MENU);

    if (isTopMenuNode != null && isTopMenuNode.isBoolean()) {
      boolean isTopMenu = isTopMenuNode.asBoolean();
      String displayType = isTopMenu ? DashboardDisplayType.TOP_MENU.getDashboardDisplayType()
          : DashboardDisplayType.SUB_MENU.getDashboardDisplayType();

      objectNode.put(SELECTED_DASHBOARD_DISPLAY_TYPE, displayType);
      objectNode.remove(IS_TOP_MENU);
    } else if (!objectNode.has(SELECTED_DASHBOARD_DISPLAY_TYPE)) {
      // Only fill in a default when the field is genuinely missing (true legacy data). If it's
      // already present - e.g. this converter runs again on data that was already migrated, or the
      // user explicitly chose a display type through the UI - leave it untouched instead of
      // clobbering it back to a default on every re-migration.
      String defaultDisplayType = isDefaultTaskOrCaseList ? DashboardDisplayType.TOP_MENU.getDashboardDisplayType()
          : DashboardDisplayType.SUB_MENU.getDashboardDisplayType();
      objectNode.put(SELECTED_DASHBOARD_DISPLAY_TYPE, defaultDisplayType);
    }

    if (isDefaultTaskOrCaseList && objectNode.has("widgets")) {
      for (JsonNode widgetNode : objectNode.withArray("widgets")) {
        ObjectNode widgetObject = (ObjectNode) widgetNode;

        if (widgetObject.has("columns")) {
          ArrayNode columns = (ArrayNode) widgetObject.get("columns");

          boolean hasPin = false;
          for (JsonNode column : columns) {
            if (PIN.equals(column.path("field").asText())) {
              hasPin = true;
              break;
            }
          }

          if (!hasPin) {
            ObjectNode pinColumn = columns.objectNode();
            pinColumn.put("field", PIN);
            pinColumn.put("width", "75");

            ArrayNode updatedColumns = columns.arrayNode();
            int insertIndex = 1;

            for (int i = 0; i < columns.size(); i++) {
              if (i == insertIndex) {
                updatedColumns.add(pinColumn);
              }
              updatedColumns.add(columns.get(i));
            }

            if (insertIndex >= columns.size()) {
              updatedColumns.add(pinColumn);
            }

            widgetObject.set("columns", updatedColumns);
          }
        }
      }
    }
  }
}
