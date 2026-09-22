package com.axonivy.portal.migration.dashboardtemplate.converter.v112;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.axonivy.portal.migration.common.BusinessStateMigrationUtils;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.fasterxml.jackson.databind.JsonNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardTemplateCaseWidgetConverter implements IJsonConverter {

  @Override
  public AbstractJsonVersion version() {
    return new DashboardTemplateJsonVersion("11.2.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
      new JsonWidgetSearch(jsonNode)
      .type(DashboardWidgetType.CASE.name())
      .findColumns().forEach(columns -> {
        columns.elements().forEachRemaining(col -> {
          if (col.get("field").asText().contentEquals(DashboardStandardCaseColumn.STATE.getField())) {
            BusinessStateMigrationUtils.convertStatesArrayInPlace(col.get("filterList"),
                BusinessStateMigrationUtils::convertCaseBusinessState);
          }
        });
      });
  }
}
