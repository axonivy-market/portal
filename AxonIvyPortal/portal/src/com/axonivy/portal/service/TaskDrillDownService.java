package com.axonivy.portal.service;

import java.util.List;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;

public class TaskDrillDownService extends AbstractDrillDownService {

  private static TaskDrillDownService instance;

  public static TaskDrillDownService getInstance() {
    if (instance == null) {
      instance = new TaskDrillDownService();
    }
    return instance;
  }

  @Override
  protected String getDashboardFilterFieldByAggregationField(String field) {
    return switch (field) {
      case StatisticConstants.RESPONSIBLE_NAME -> DashboardStandardTaskColumn.RESPONSIBLE.getField();
      default -> field;
    };
  }

  @Override
  protected Dashboard getDrillDownDashboard() {
    return DefaultDashboardUtils.getTaskDrillDownDashboard();
  }

  @Override
  protected List<DashboardFilter> getWidgetFilters(DashboardWidget widget) {
    return ((TaskDashboardWidget) widget).getFilters();
  }

  @Override
  protected List<? extends ColumnModel> getWidgetColumns(DashboardWidget widget) {
    return ((TaskDashboardWidget) widget).getColumns();
  }

  @Override
  protected ColumnModel createWidgetColumn(DashboardColumnType fieldType, String field) {
    return TaskColumnModel.constructColumn(fieldType, field);
  }

  @Override
  protected void addStatisticFiltersToWidgetFilters(DashboardWidget widget, List<DashboardFilter> filters) {
    for (DashboardFilter filter : filters) {
      if (StatisticConstants.CAN_WORK_ON.equals(filter.getField())) {
        ((TaskDashboardWidget) widget).setCanWorkOn(true);
      } else {
        getWidgetFilters(widget).add(filter);
      }
    }
  }

}
