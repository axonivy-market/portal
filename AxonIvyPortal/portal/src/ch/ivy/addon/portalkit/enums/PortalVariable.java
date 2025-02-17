package ch.ivy.addon.portalkit.enums;

public enum PortalVariable {
  PASSWORD_VALIDATION("Portal.PasswordValidation"),
  ANNOUNCEMENT("Portal.Announcement"),
  THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
  WIDGET_FILTER("Portal.Dashboard.WidgetFilters"),
  EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
  TASK_DETAIL("Portal.TaskDetails"),
  CASE_DETAIL("Portal.CaseDetails"),
  DASHBOARD("Portal.Dashboard"),
  DASHBOARD_ORDER("Portal.Dashboard.Order"),
  DASHBOARD_TEMPLATES("Portal.DashboardTemplates"),
  USER_MENU("Portal.UserMenu"), 
  CUSTOM_MENU_ITEMS("Portal.CustomMenuItems"),
  STATISTIC_DATA("Portal.StatisticData"),
  CHATBOT_ENDPOINT("PortalAiUrl"),
  DASHBOARD_MAIN_MENU_ENTRY("Portal.Dashboard.MainMenuEntry"),
  CLIENT_STATISTIC("Portal.ClientStatistic"),
  CUSTOM_CLIENT_STATISTIC("Portal.CustomClientStatistic")
  ;

  public String key;

  private PortalVariable(String key) {
    this.key = key;
  }

}
