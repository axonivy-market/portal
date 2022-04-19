package ch.ivy.addon.portalkit.enums;

public enum PortalVariable {
  ANNOUNCEMENT("Portal.Announcement"),
  THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
  STATISTIC_CHART("Portal.StatisticCharts"),
  FAVORITE_PROCESS("Portal.Dashboard.FavoriteProcesses"),
  WIDGET_FILTER("Portal.Dashboard.WidgetFilters"),
  EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
  EXPRESS_PROCESS("Portal.Processes.ExpressProcesses"),
  TASK_DETAIL("Portal.TaskDetails"),
  CASE_DETAIL("Portal.CaseDetails"),
  DASHBOARD("Portal.Dashboard"),
  DASHBOARD_ORDER("Portal.Dashboard.Order"),
  DASHBOARD_TEMPLATES("Portal.DashboardTemplates");

  public String key;

  private PortalVariable(String key) {
    this.key = key;
  }

}
