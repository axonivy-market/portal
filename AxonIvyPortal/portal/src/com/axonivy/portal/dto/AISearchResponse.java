package com.axonivy.portal.dto;

import java.util.List;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

public class AISearchResponse {

  private double confidence;
  private String rephrased;
  private String clarification;
  private List<DashboardFilter> taskFilters;
  private List<DashboardFilter> caseFilters;

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public String getRephrased() {
    return rephrased;
  }

  public void setRephrased(String rephrased) {
    this.rephrased = rephrased;
  }

  public String getClarification() {
    return clarification;
  }

  public void setClarification(String clarification) {
    this.clarification = clarification;
  }

  public List<DashboardFilter> getTaskFilters() {
    return taskFilters;
  }

  public void setTaskFilters(List<DashboardFilter> taskFilters) {
    this.taskFilters = taskFilters;
  }

  public List<DashboardFilter> getCaseFilters() {
    return caseFilters;
  }

  public void setCaseFilters(List<DashboardFilter> caseFilters) {
    this.caseFilters = caseFilters;
  }
}
