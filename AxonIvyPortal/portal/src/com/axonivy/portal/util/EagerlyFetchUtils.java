package com.axonivy.portal.util;

import java.util.List;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public final class EagerlyFetchUtils {

  public static final int MAX_EAGERLY_FETCH_COUNT = 1000;

  private EagerlyFetchUtils() {
  }

  public static boolean shouldEagerlyFetch(int count) {
    return count > 0 && count <= MAX_EAGERLY_FETCH_COUNT;
  }

  public static TaskQuery eagerlyFetchDataOfColumns(TaskQuery query, List<TaskColumnModel> columns) {
    if (columns == null) {
      return query;
    }
    for (TaskColumnModel column : columns) {
      if (column.getType() == null) {
        continue;
      }
      switch (column.getType()) {
        case STANDARD -> eagerlyFetchStandardTaskColumn(query, column);
        case CUSTOM -> eagerlyFetchCustomFields(column, query.eagerlyFetch().tasks()::customStringFields,
            query.eagerlyFetch().tasks()::customNumberFields, query.eagerlyFetch().tasks()::customTimestampFields);
        case CUSTOM_CASE -> eagerlyFetchCustomFields(column, query.eagerlyFetch().cases()::customStringFields,
            query.eagerlyFetch().cases()::customNumberFields, query.eagerlyFetch().cases()::customTimestampFields);
        case CUSTOM_BUSINESS_CASE -> eagerlyFetchCustomFields(column,
            query.eagerlyFetch().businessCases()::customStringFields,
            query.eagerlyFetch().businessCases()::customNumberFields,
            query.eagerlyFetch().businessCases()::customTimestampFields);
      }
    }
    return query;
  }

  public static CaseQuery eagerlyFetchDataOfColumns(CaseQuery query, List<CaseColumnModel> columns) {
    if (columns == null) {
      return query;
    }
    for (CaseColumnModel column : columns) {
      if (column.getType() == DashboardColumnType.STANDARD) {
        eagerlyFetchStandardCaseColumn(query, column);
      } else if (column.isCustomActionColumn()) {
        query.eagerlyFetch().customStringFields();
        query.eagerlyFetch().customNumberFields();
        query.eagerlyFetch().customTimestampFields();
      } else if (column.getType() == DashboardColumnType.CUSTOM) {
        eagerlyFetchCustomFields(column, query.eagerlyFetch()::customStringFields,
            query.eagerlyFetch()::customNumberFields, query.eagerlyFetch()::customTimestampFields);
      }
    }
    return query;
  }

  public static TaskQuery eagerlyFetchDataOfColumnNames(TaskQuery query, List<String> columns) {
    if (columns == null) {
      return query;
    }
    for (String column : columns) {
      if (TaskSortField.NAME.name().equalsIgnoreCase(column)) {
        query.eagerlyFetch().tasks().nameAndDescription();
      } else if (TaskSortField.CATEGORY.name().equalsIgnoreCase(column)
          || TaskSortField.TECHNICAL_CASE_ID.name().equalsIgnoreCase(column)) {
        query.eagerlyFetch().cases().data();
      } else if (TaskSortField.BUSINESS_CASE_ID.name().equalsIgnoreCase(column)) {
        query.eagerlyFetch().businessCases().data();
      }
    }
    return query;
  }

  public static CaseQuery eagerlyFetchDataOfColumnNames(CaseQuery query, List<String> columns) {
    if (columns == null) {
      return query;
    }
    for (String column : columns) {
      if (CaseSortField.NAME.name().equalsIgnoreCase(column)) {
        query.eagerlyFetch().nameAndDescription();
      } else if (CaseSortField.OWNER.name().equalsIgnoreCase(column)) {
        query.eagerlyFetch().owners();
      }
    }
    return query;
  }

  private static void eagerlyFetchStandardCaseColumn(CaseQuery query, CaseColumnModel column) {
    if (DashboardStandardCaseColumn.NAME.getField().equalsIgnoreCase(column.getField())
        || DashboardStandardCaseColumn.DESCRIPTION.getField().equalsIgnoreCase(column.getField())) {
      query.eagerlyFetch().nameAndDescription();
    } else if (DashboardStandardCaseColumn.OWNER.getField().equalsIgnoreCase(column.getField())) {
      query.eagerlyFetch().owners();
    }
  }

  private static void eagerlyFetchStandardTaskColumn(TaskQuery query, TaskColumnModel column) {
    if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(column.getField())
        || DashboardStandardTaskColumn.DESCRIPTION.getField().equalsIgnoreCase(column.getField())) {
      query.eagerlyFetch().tasks().nameAndDescription();
    } else if (DashboardStandardTaskColumn.TECHNICAL_CASE_ID.getField().equalsIgnoreCase(column.getField())
        || DashboardStandardTaskColumn.CATEGORY.getField().equalsIgnoreCase(column.getField())) {
      query.eagerlyFetch().cases().data();
    } else if (DashboardStandardTaskColumn.BUSINESS_CASE_ID.getField().equalsIgnoreCase(column.getField())) {
      query.eagerlyFetch().businessCases().data();
    }
  }

  private static void eagerlyFetchCustomFields(ColumnModel column, Runnable stringFields, Runnable numberFields,
      Runnable timestampFields) {
    if (column.isNumber()) {
      numberFields.run();
    } else if (column.isDate()) {
      timestampFields.run();
    } else if (!column.isText()) {
      stringFields.run();
    }
  }
}
