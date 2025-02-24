package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;

public class ExportUtils {

  private final static String CUSTOM_FIELD_FORMAT = "%s__%s__%s__%s";
  private final static String FIRST_COLUMN_OF_TASK_DASHBOARD_WIDGET = "start";

  /**
   * Build dashboard task/case widget's column visibility list for export function
   * 
   * @param columns
   * @return column list
   */
  public static List<String> buildVisibleColumns(List<ColumnModel> columns){
    List<String> visibleColumns = new ArrayList<>();
    for (ColumnModel column : columns) {
      if (column.getVisible() && !column.getField().contentEquals(DashboardStandardCaseColumn.ACTIONS.getField())) {
        if (column.getType() == DashboardColumnType.CUSTOM || column.getType() == DashboardColumnType.CUSTOM_CASE
            || column.getType() == DashboardColumnType.CUSTOM_BUSINESS_CASE) {
          visibleColumns.add(
              String.format(CUSTOM_FIELD_FORMAT, column.getFormat().name(), column.getField(), column.getHeader(),
                  column.getType()));
        } else {
          visibleColumns.add(column.getField());
        }
      }
    }
    if (columns.get(0).getField().equals(FIRST_COLUMN_OF_TASK_DASHBOARD_WIDGET)) {
      visibleColumns.remove(FIRST_COLUMN_OF_TASK_DASHBOARD_WIDGET);
    }
    return visibleColumns;
  }
}
