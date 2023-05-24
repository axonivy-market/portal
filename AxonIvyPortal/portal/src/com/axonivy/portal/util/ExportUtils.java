package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;

public class ExportUtils {

  private final static String CUSTOM_FIELD_FORMAT = "%s__%s__%s";

  /**
   * Build dashboard case widget's column visibility list for export function
   * 
   * @param columns
   * @return
   */
  public static List<String> buildVisibilityColumnsForDashboardCaseWidget(List<CaseColumnModel> columns) {
    List<String> columnsVisibility = new ArrayList<>();
    for(CaseColumnModel column : columns) {
      if (column.getVisible() == true && !column.getField().contentEquals(DashboardStandardCaseColumn.ACTIONS.getField())) {
        if (column.getType() == DashboardColumnType.CUSTOM) {
          columnsVisibility.add(String.format(CUSTOM_FIELD_FORMAT, column.getFormat().name(), column.getField(), column.getHeader()));
        } else {
          columnsVisibility.add(column.getField());
        }
      }
    }

    return columnsVisibility;
  }
}
