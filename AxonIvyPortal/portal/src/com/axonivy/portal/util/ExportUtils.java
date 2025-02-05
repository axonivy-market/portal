package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;

public class ExportUtils {

  private final static String CUSTOM_FIELD_FORMAT = "%s__%s__%s__%s";
  private final static String START_COLUMN = "start";
  private final static String ACTIONS_COLUMN = "actions";

  /**
   * Build dashboard task/case widget's column visibility list for export function
   * 
   * @param columns
   * @return
   */
  public static List<String> buildVisibleColumns(List<ColumnModel> columns){
    List<String> visibleColumns = new ArrayList<>();
    for (ColumnModel column : columns) {
      if (column.getVisible()) {
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
    if (visibleColumns.contains(START_COLUMN)) {
      visibleColumns.remove(START_COLUMN);
    }
    if (visibleColumns.contains(ACTIONS_COLUMN)) {
      visibleColumns.remove(ACTIONS_COLUMN);
    }
    return visibleColumns;
  }
}
