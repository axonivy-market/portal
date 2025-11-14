package com.axonivy.portal.service;

import java.util.List;

import java.util.Set;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.components.service.LanguageService;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskDrillDownService extends AbstractDrillDownService {

  private static final String TASK_DRILL_DOWN_WIDGET_ID = "task-drill-down-widget-id";
  private static final Set<String> FIELDS_USING_IN_OPERATOR = Set.of(DashboardStandardTaskColumn.STATE.getField(), DashboardStandardTaskColumn.CATEGORY.getField(), DashboardStandardTaskColumn.PRIORITY.getField(), DashboardStandardTaskColumn.RESPONSIBLE.getField());
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
    Dashboard taskDrillDownDashboard = DefaultDashboardUtils.getTaskDrillDownDashboard();
    taskDrillDownDashboard.setTitle(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/tasWidgetkInfo/taskDrillDownDashboard"));
    taskDrillDownDashboard.getWidgets().getFirst().setId(TASK_DRILL_DOWN_WIDGET_ID);
    return taskDrillDownDashboard;
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

  @Override
  protected void buildWidgetName(Statistic statistic, DashboardWidget widget) {
    String statisticChartName = statistic.getNames().stream().filter(item -> LanguageService.getInstance().getUserLocale().equals(item.getLocale())).findFirst().map(value -> value.getValue()).orElse(""); 
    widget.setName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DrillDownWidget/taskDrillDown", List.of(statisticChartName)));    
  }

  @Override
  protected boolean shouldUseInOperator(String field) {
    return FIELDS_USING_IN_OPERATOR.contains(field);
  }

  @Override
  public void removeWidgetFilterFromSession() {
    WidgetFilterService.getInstance().removeWidgetFilterFromSession(TASK_DRILL_DOWN_WIDGET_ID, DashboardWidgetType.TASK);
  }
}
