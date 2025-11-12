package com.axonivy.portal.service;

import java.util.List;
import java.util.Set;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.components.service.LanguageService;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class CaseDrillDownService extends AbstractDrillDownService {
  
  private static final Set<String> FIELDS_USING_IN_OPERATOR = Set.of(DashboardStandardCaseColumn.STATE.getField(), DashboardStandardCaseColumn.CATEGORY.getField(), StatisticConstants.CREATOR_NAME);
  private static CaseDrillDownService instance;
  
  public static CaseDrillDownService getInstance() {
    if (instance == null) {
      instance = new CaseDrillDownService();
    }
    return instance;
  }

  @Override
  protected String getDashboardFilterFieldByAggregationField(String field) {
    return switch (field) {
      case StatisticConstants.CREATOR_NAME -> DashboardStandardCaseColumn.CREATOR.getField();
      default -> field;
    };
  }

  @Override
  protected Dashboard getDrillDownDashboard() {
    Dashboard caseDrillDownDashboard = DefaultDashboardUtils.getCaseDrillDownDashboard();
    caseDrillDownDashboard.setTitle(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/caseDrillDownDashboard"));
    caseDrillDownDashboard.getWidgets().getFirst().setId(DashboardWidgetUtils.generateNewWidgetId(DashboardWidgetType.CASE));
    return caseDrillDownDashboard;
  }

  @Override
  protected List<DashboardFilter> getWidgetFilters(DashboardWidget widget) {
    return ((CaseDashboardWidget) widget).getFilters();
  }

  @Override
  protected List<CaseColumnModel> getWidgetColumns(DashboardWidget widget) {
    return ((CaseDashboardWidget) widget).getColumns();
  }

  @Override
  protected ColumnModel createWidgetColumn(DashboardColumnType fieldType, String field) {
    return CaseColumnModel.constructColumn(fieldType, field);
  }

  @Override
  protected void addStatisticFiltersToWidgetFilters(DashboardWidget widget, List<DashboardFilter> filters) {
    getWidgetFilters(widget).addAll(filters);
  }

  @Override
  protected void buildWidgetName(Statistic statistic, DashboardWidget widget) {
    String statisticChartName = statistic.getNames().stream().filter(item -> LanguageService.getInstance().getUserLocale().equals(item.getLocale())).findFirst().map(value -> value.getValue()).orElse(""); 
    widget.setName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DrillDownWidget/caseDrillDown", List.of(statisticChartName)));
  }

  @Override
  protected boolean shouldUseInOperator(String field) {
    return FIELDS_USING_IN_OPERATOR.contains(field);
  }

}
