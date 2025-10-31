package com.axonivy.portal.service;

import java.util.List;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;

public class CaseDrillDownService extends AbstractDrillDownService {

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
    return DefaultDashboardUtils.getCaseDrillDownDashboard();
  }

  @Override
  protected List<DashboardFilter> getWidgetFilters(DashboardWidget widget) {
    return ((CaseDashboardWidget) widget).getFilters();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  protected List getWidgetColumns(DashboardWidget widget) {
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

}
