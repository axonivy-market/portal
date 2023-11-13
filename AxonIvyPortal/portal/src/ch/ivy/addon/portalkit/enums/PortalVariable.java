package ch.ivy.addon.portalkit.enums;

public enum PortalVariable {
  PASSWORD_VALIDATION("Portal.PasswordValidation"),
  ANNOUNCEMENT("Portal.Announcement"),
  THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
  STATISTIC_CHART("Portal.StatisticCharts"),
  WIDGET_FILTER("Portal.Dashboard.WidgetFilters"),
  EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
  EXPRESS_PROCESS("Portal.Processes.ExpressProcesses"),
  TASK_DETAIL("Portal.TaskDetails"),
  CASE_DETAIL("Portal.CaseDetails"),
  DASHBOARD("Portal.Dashboard"),
  DASHBOARD_ORDER("Portal.Dashboard.Order"),
  DASHBOARD_TEMPLATES("Portal.DashboardTemplates"),
  USER_MENU("Portal.UserMenu"), 
  CUSTOM_MENU_ITEMS("Portal.CustomMenuItems"),
  CLIENT_STATISTIC("Portal.ClientStatistic");

  public String key;

  private PortalVariable(String key) {
    this.key = key;
  }

}
