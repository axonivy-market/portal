package com.axonivy.portal.migration.version112;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;

public class CaseBusinessStateMigrationService {
  
  private CaseBusinessStateMigrationService() {}

  /**
   * Adapt case business state for dashboards
   * IVYPORTAL-14663: Introduce CaseBusinessState
   * 
   * @param dashboard
   */
  public static void adaptCaseBusinessStateForDashboard(Dashboard dashboard) {
    dashboard.getWidgets().forEach(widget -> {
      if (widget instanceof CaseDashboardWidget) {
        CaseDashboardWidget caseWidget = (CaseDashboardWidget) widget;
        CaseColumnModel stateColumn = caseWidget.getColumns().stream()
            .filter(col -> col.getField().contentEquals(DashboardStandardCaseColumn.STATE.getField())).findFirst().orElseGet(null);

        if (stateColumn != null) {
          stateColumn.setFilterList(updateFilters(stateColumn.getFilterList()));
        }
      }
    });
  }

  /**
   * Adapt case business state for widget filter sets
   * IVYPORTAL-14663: Introduce CaseBusinessState
   * 
   * @param dashboard
   */
  public static void adaptCaseBusinessStateForWidgetFilterSets(List<WidgetFilterModel> widgetFilters) {
    widgetFilters.stream().filter(f -> f.getWidgetType() == DashboardWidgetType.CASE).forEach(filter -> {
      filter.getFilterableColumns().stream()
        .filter(col -> col.getField().contentEquals("state"))
        .forEach(col -> {
          col.setFilterList(updateFilters(col.getUserFilterList()));
      });
      WidgetFilterService.getInstance().save(filter);
    });
  }


  private static List<String> updateFilters(List<String> oldFilters) {
    List<String> newFilters = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(oldFilters)) {
      if (oldFilters.contains(CaseState.RUNNING.name()) || oldFilters.contains(CaseState.CREATED.name())) {
        newFilters.add(CaseBusinessState.OPEN.name());
      }
      if (oldFilters.contains(CaseState.DONE.name())) {
        newFilters.add(CaseBusinessState.DONE.name());
      }
      if (oldFilters.contains(CaseState.DESTROYED.name())) {
        newFilters.add(CaseBusinessState.DESTROYED.name());
      }
    }
    return newFilters;
  }
}
