package com.axonivy.portal.developerexamples.enums;

public enum StatisticChartType {
  TASK_BY_PRIORITY("/Labels/statistic/chartType/taskByPriority", null, "taskByPriorityChartExtender"),
  CASES_BY_STATE("/Labels/statistic/chartType/caseByState", null, "caseByStateChartExtender"),
  CASES_BY_FINISHED_TASK("/Labels/statistic/chartType/caseByFinishedTask", "/Labels/statistic/chartType/caseByFinishedTaskDescription", "caseByStateFinishTaskChartExtender"),
  CASES_BY_FINISHED_TIME("/Labels/statistic/chartType/caseByFinishedTime", "/Labels/statistic/chartType/caseByFinishedTimeDescription", "caseByStateFinishTimeChartExtender"),
  TASK_BY_EXPIRY("/Labels/statistic/chartType/taskByExpiry", null, "taskByExpiryChartDefaultExtender"),
  ELAPSED_TIME_BY_CASE_CATEGORY("/Labels/statistic/chartType/elapsedTimeByCaseCategory", "/Labels/statistic/chartType/elapsedTimeByCaseCategoryDescription", "elapsedTimeChartExtender"),
  CASES_BY_CATEGORY("/Labels/statistic/chartType/casesByCategory", null, "casesByCategoryChartExtender");

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
