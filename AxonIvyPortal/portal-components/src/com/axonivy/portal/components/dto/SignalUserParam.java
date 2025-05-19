package com.axonivy.portal.components.dto;

import java.util.Collections;
import java.util.List;

public class SignalUserParam {
  private String query;
  private int startIndex;
  private int count;
  private List<String> fromRoles;
  private List<String> excludedUsernames;

  public SignalUserParam() {
    this.query = "";
    this.startIndex = 0;
    this.count = -1;
    this.fromRoles = Collections.emptyList();
    this.excludedUsernames = Collections.emptyList();
  }
  
  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<String> getFromRoles() {
    return fromRoles;
  }

  public void setFromRoles(List<String> fromRoles) {
    this.fromRoles = fromRoles;
  }

  public List<String> getExcludedUsernames() {
    return excludedUsernames;
  }

  public void setExcludedUsernames(List<String> excludedUsernames) {
    this.excludedUsernames = excludedUsernames;
  }

}
