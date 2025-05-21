package com.axonivy.portal.migration.dashboard.converter.v131;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;

public class DashboardConverter implements IJsonConverter {

  private static final String SELECTED_DASHBOARD_DISPLAY_TYPE = "dashboardDisplayType";
  private static final String IS_TOP_MENU = "isTopMenu";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("13.1.0");
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
    } else if (isTopMenuNode == null && isDefaultTaskOrCaseList) {
      objectNode.put(SELECTED_DASHBOARD_DISPLAY_TYPE, DashboardDisplayType.TOP_MENU.getDashboardDisplayType());
    } else {
      objectNode.put(SELECTED_DASHBOARD_DISPLAY_TYPE, DashboardDisplayType.SUB_MENU.getDashboardDisplayType());
    }

  }
}
