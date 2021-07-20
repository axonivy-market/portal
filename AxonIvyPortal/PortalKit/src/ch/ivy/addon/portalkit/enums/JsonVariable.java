package ch.ivy.addon.portalkit.enums;

public enum JsonVariable {
  ANNOUNCEMENT("Portal.Announcement"),
  TASK_ANALYSIS_FILTER("Portal.TaskAnalysisFilters"),
  THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
  STATISTIC_CHART("Portal.StatisticCharts"),
  FAVORITE_PROCESS("Portal.Dashboard.FavoriteProcesses"),
  EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
  EXPRESS_PROCESS("Portal.Processes.ExpressProcesses"),
  TASK_COLUMN("Portal.Tasks.TaskColumn"),
  TASK_FILTER("Portal.Tasks.TaskFilters"),
  TASK_DETAIL("Portal.TaskDetails"),
  CASE_COLUMN("Portal.Cases.CaseColumn"),
  CASE_FILTER( "Portal.Cases.CaseFilters"),
  CASE_DETAIL("Portal.CaseDetails"),
  DASHBOARD("Portal.Dashboard");

  public String key;

  private JsonVariable(String key) {
    this.key = key;
  }

}
