package portalmigration.enums;

public enum PortalVariable {
  ANNOUNCEMENT("Portal.Announcement"),
  THIRD_PARTY_APP("Portal.ThirdPartyApplications"),
  STATISTIC_CHART("Portal.StatisticCharts"),
  FAVORITE_PROCESS("Portal.Dashboard.FavoriteProcesses"),
  EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
  EXPRESS_PROCESS("Portal.Processes.ExpressProcesses"),
  TASK_DETAIL("Portal.TaskDetails"),
  CASE_DETAIL("Portal.CaseDetails"),
  DASHBOARD("Portal.Dashboard");

  public String key;

  private PortalVariable(String key) {
    this.key = key;
  }

}
