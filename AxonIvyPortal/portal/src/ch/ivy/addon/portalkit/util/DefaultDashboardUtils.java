package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.service.exception.PortalException;

public class DefaultDashboardUtils {

  private static final String DEFAULT_TASK_LIST_DRILL_DOWN_DASHBOARD_ID = "default-task-list-drill-down-dashboard";

  private static final String DEFAULT_CASE_LIST_DRILL_DOWN_DASHBOARD_ID = "default-case-list-drill-down-dashboard";
  
  private static final String DEFAULT_TASK_LIST_DASHBOARD_TEMPLATE_ID = "full-task-list-dashboard-template";
  private static final String DEFAULT_CASE_LIST_DASHBOARD_TEMPLATE_ID = "full-case-list-dashboard-template";

  public static Dashboard getTaskDrillDownDashboard() {
    Dashboard dashboard = DashboardUtils.getDashboardTemplateById(DEFAULT_TASK_LIST_DASHBOARD_TEMPLATE_ID)
      .orElseThrow(() -> new PortalException("Cannot find task list dashboard template for task drill down"))
      .getDashboard();
    dashboard.setId(DEFAULT_TASK_LIST_DRILL_DOWN_DASHBOARD_ID);
    dashboard.setDashboardDisplayType(DashboardDisplayType.HIDDEN);
    dashboard.setIsResponsive(true);
    return dashboard;
  }

  public static Dashboard getCaseDrillDownDashboard() {
    Dashboard dashboard = DashboardUtils.getDashboardTemplateById(DEFAULT_CASE_LIST_DASHBOARD_TEMPLATE_ID)
      .orElseThrow(() -> new PortalException("Cannot find case list dashboard template for case drill down"))
      .getDashboard();
    dashboard.setId(DEFAULT_CASE_LIST_DRILL_DOWN_DASHBOARD_ID);
    dashboard.setDashboardDisplayType(DashboardDisplayType.HIDDEN);
    dashboard.setIsResponsive(true);
    return dashboard;
  }
}
