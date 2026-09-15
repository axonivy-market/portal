package com.axonivy.portal.migration.dashboard.converter.v112;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.BusinessStateMigrationUtils;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.fasterxml.jackson.databind.JsonNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardTaskWidgetConverter implements IJsonConverter{
  public static final String DASHBOARD_VERSION = "11.2.0";
  public static final String FILTER_LIST = "filterList";
  public static final String FIELD = "field";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion(DASHBOARD_VERSION);
  }

  @Override
  public void convert(JsonNode jsonNode) {
    new JsonWidgetSearch(jsonNode)
      .type(DashboardWidgetType.TASK.name())
      .findColumns().forEach(columns -> {
        columns.elements().forEachRemaining(column -> {
          if (column.get(FIELD).asText().contentEquals(DashboardStandardTaskColumn.STATE.getField())) {
            BusinessStateMigrationUtils.convertStatesArrayInPlace(column.get(FILTER_LIST),
                BusinessStateMigrationUtils::convertTaskBusinessState);
          }
        });
      });
  }

}
