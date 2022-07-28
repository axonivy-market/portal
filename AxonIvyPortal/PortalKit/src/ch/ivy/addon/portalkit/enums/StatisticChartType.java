package ch.ivy.addon.portalkit.enums;

public enum StatisticChartType {
  TASK_BY_PRIORITY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/taskByPriority", null),
  CASES_BY_STATE("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByState", null),
  CASES_BY_FINISHED_TASK("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTask", "/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTaskDescription"),
  CASES_BY_FINISHED_TIME("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTime", "/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTimeDescription"),
  TASK_BY_EXPIRY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/taskByExpiry", null),
  ELAPSED_TIME_BY_CASE_CATEGORY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/elapsedTimeByCaseCategory", "/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/elapsedTimeByCaseCategoryDescription"),
  CASES_BY_CATEGORY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByCategory", null);

  private String cmsUri;
  private String cmsDescriptionUri;

  private StatisticChartType(String cmsUri, String cmsDescriptionUri) {
    this.cmsUri = cmsUri;
    this.cmsDescriptionUri = cmsDescriptionUri;
  }

  public String getCmsUri() {
    return cmsUri;
  }

  public String getCmsDescriptionUri() {
    return cmsDescriptionUri;
  }
  
}
