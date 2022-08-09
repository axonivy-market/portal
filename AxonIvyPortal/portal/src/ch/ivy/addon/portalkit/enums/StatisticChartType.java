package ch.ivy.addon.portalkit.enums;

public enum StatisticChartType {
  TASK_BY_PRIORITY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/taskByPriority", null, "taskByPriorityChartExtender"),
  CASES_BY_STATE("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByState", null, "caseByStateChartExtender"),
  CASES_BY_FINISHED_TASK("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTask", "/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTaskDescription", "caseByStateFinishTaskChartExtender"),
  CASES_BY_FINISHED_TIME("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTime", "/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/caseByFinishedTimeDescription", "caseByStateFinishTimeChartExtender"),
  TASK_BY_EXPIRY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/taskByExpiry", null, "taskByExpiryChartDefaultExtender"),
  ELAPSED_TIME_BY_CASE_CATEGORY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/elapsedTimeByCaseCategory", "/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/elapsedTimeByCaseCategoryDescription", "elapsedTimeChartExtender"),
  CASES_BY_CATEGORY("/ch.ivy.addon.portalkit.ui.jsf/statistic/chartType/casesByCategory", null, "casesByCategoryChartExtender");

  private String cmsUri;
  private String cmsDescriptionUri;
  private String chartExtender;

  private StatisticChartType(String cmsUri, String cmsDescriptionUri, String chartExtender) {
    this.cmsUri = cmsUri;
    this.cmsDescriptionUri = cmsDescriptionUri;
    this.chartExtender = chartExtender;
  }

  public String getCmsUri() {
    return cmsUri;
  }

  public String getCmsDescriptionUri() {
    return cmsDescriptionUri;
  }

  public String getChartExtender() {
    return chartExtender;
  }
  
}
