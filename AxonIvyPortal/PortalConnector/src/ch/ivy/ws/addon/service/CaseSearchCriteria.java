package ch.ivy.ws.addon.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

public class CaseSearchCriteria {

  private String jsonQuery;
  private String involvedUsername;
  private List<String> involvedApplications;
  private boolean ignoreInvolvedUser;
  private boolean isBusinessCase;
  private boolean isTechnicalCase;
  private Long businessCaseId;

  public boolean isEmpty() {
    return !hasInvolvedUsername() && !hasInvolvedApplications() && !hasJsonQuery();
  }

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public List<String> getInvolvedApplications() {
    return involvedApplications;
  }

  public void setInvolvedApplications(List<String> involvedApplications) {
    this.involvedApplications = involvedApplications;
  }

  public boolean hasInvolvedApplications() {
    return involvedApplications != null && !involvedApplications.isEmpty();
  }

  public boolean hasInvolvedUsername() {
    return !(Objects.toString(involvedUsername, "").isEmpty());
  }

  public boolean isIgnoreInvolvedUser() {
    return ignoreInvolvedUser;
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    this.ignoreInvolvedUser = ignoreInvolvedUser;
  }

  public boolean isBusinessCase() {
    return isBusinessCase;
  }

  public void setBusinessCase(boolean isBusinessCase) {
    this.isBusinessCase = isBusinessCase;
  }

  public boolean isTechnicalCase() {
    return isTechnicalCase;
  }

  public void setTechnicalCase(boolean isTechnicalCase) {
    this.isTechnicalCase = isTechnicalCase;
  }

  public Long getBusinessCaseId() {
	return businessCaseId;
  }

  public void setBusinessCaseId(Long businessCaseId) {
	this.businessCaseId = businessCaseId;
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
  
}
