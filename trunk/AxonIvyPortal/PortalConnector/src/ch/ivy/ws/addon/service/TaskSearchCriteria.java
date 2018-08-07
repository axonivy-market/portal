package ch.ivy.ws.addon.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class TaskSearchCriteria {

  private String jsonQuery;
  private String involvedUsername;
  private List<String> involvedApplications;
  private boolean queryByTaskId;
  private boolean ignoreInvolvedUser;

  public boolean isEmpty() {
    return !hasInvolvedUsername()&& !hasInvolvedApplications() && !hasJsonQuery();
  }

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public String getJsonQuery() {
    return jsonQuery;
  }

  public void setJsonQuery(String jsonQuery) {
    this.jsonQuery = jsonQuery;
  }

  public boolean hasJsonQuery() {
    return StringUtils.isNotBlank(jsonQuery);
  }

  public boolean hasInvolvedUsername() {
    return StringUtils.isNotBlank(involvedUsername);
  }
  
  public List<String> getInvolvedApplications() {
    return involvedApplications;
  }

  public void setInvolvedApplications(List<String> involvedApplications) {
    this.involvedApplications = involvedApplications;
  }
  
  public boolean hasInvolvedApplications() {
    return CollectionUtils.isNotEmpty(involvedApplications);
  }

  public boolean isQueryByTaskId() {
    return queryByTaskId;
  }

  public void setQueryByTaskId(boolean queryByTaskId) {
    this.queryByTaskId = queryByTaskId;
  }

  public boolean isIgnoreInvolvedUser() {
    return ignoreInvolvedUser;
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    this.ignoreInvolvedUser = ignoreInvolvedUser;
  }
}
