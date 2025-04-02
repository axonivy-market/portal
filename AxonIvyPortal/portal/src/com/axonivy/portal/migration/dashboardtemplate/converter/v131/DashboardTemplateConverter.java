package com.axonivy.portal.migration.dashboardtemplate.converter.v131;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.DashboardDisplayType;

public class DashboardTemplateConverter implements IJsonConverter {

  private static final String SELECTED_DASHBOARD_DISPLAY_TYPE = "dashboardDisplayType";
  private static final String IS_TOP_MENU = "isTopMenu";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardTemplateJsonVersion("13.1.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    jsonNode.forEach(dashboard -> {
      ObjectNode dashboardObjectNode = (ObjectNode) dashboard;
      JsonNode isTopMenuValue = dashboardObjectNode.get(IS_TOP_MENU);

      if (isTopMenuValue != null && isTopMenuValue.isBoolean()) {
        boolean isTopMenu = isTopMenuValue.asBoolean();

        String displayType = isTopMenu ? DashboardDisplayType.TOP_MENU.getDashboardDisplayType()
            : DashboardDisplayType.SUB_MENU.getDashboardDisplayType();

        dashboardObjectNode.put(SELECTED_DASHBOARD_DISPLAY_TYPE, displayType);
        dashboardObjectNode.remove(IS_TOP_MENU);
      } else {
        dashboardObjectNode.put(SELECTED_DASHBOARD_DISPLAY_TYPE, DashboardDisplayType.SUB_MENU.getDashboardDisplayType());
      }
    });
  }
}
