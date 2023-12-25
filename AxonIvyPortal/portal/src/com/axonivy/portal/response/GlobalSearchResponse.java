package com.axonivy.portal.response;

import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;

public class GlobalSearchResponse {
  private List<? extends Object> results;
  private long totalResult;
  private String viewAllText;

  public GlobalSearchResponse(List<? extends Object> results, long totalResult) {
    this.results = results;
    this.totalResult = totalResult;
    this.viewAllText = Ivy.cms().co("/Dialogs/ch/ivy/addon/portal/generic/GlobalSearch/ViewAllResults");
  }

  public String getViewAllText() {
    return viewAllText;
  }

  public void setViewAllText(String viewAllText) {
    this.viewAllText = viewAllText;
  }

  public List<? extends Object> getResults() {
    return results;
  }

  public void setResults(List<? extends Object> results) {
    this.results = results;
  }

  public long getTotalResult() {
    return totalResult;
  }

  public void setTotalResult(long totalResult) {
    this.totalResult = totalResult;
  }

}
