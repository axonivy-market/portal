package ch.ivy.addon.portalkit.enums;

public enum StatisticChartType {
  TASK_BY_PRIORITY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/taskByPriority"),
  CASES_BY_STATE("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByState"),
  CASES_BY_FINISHED_TASK("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTask"),
  CASES_BY_FINISHED_TIME("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTime"),
  TASK_BY_EXPIRY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/taskByExpiry"),
  ELAPSED_TIME_BY_CASE_CATEGORY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/elapsedTimeByCaseCategory");

  private String cmsUri;

  private StatisticChartType(String cmsUri) {
    this.cmsUri = cmsUri;
  }

  public String getCmsUri() {
    return cmsUri;
  }
}
