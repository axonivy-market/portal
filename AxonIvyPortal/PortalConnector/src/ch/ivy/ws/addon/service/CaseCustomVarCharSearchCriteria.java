package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.enums.PortalCaseCustomVarField;
import ch.ivy.ws.addon.types.IvyApplication;

public class CaseCustomVarCharSearchCriteria {
  private PortalCaseCustomVarField portalCaseCustomVarField;
  private int limit;
  private String keyword;
  List<IvyApplication> applications;
  
  public PortalCaseCustomVarField getPortalCaseCustomVarField() {
    return portalCaseCustomVarField;
  }
  public void setPortalCaseCustomVarField(PortalCaseCustomVarField portalCaseCustomVarField) {
    this.portalCaseCustomVarField = portalCaseCustomVarField;
  }
  public int getLimit() {
    return limit;
  }
  public void setLimit(int limit) {
    this.limit = limit;
  }
  public String getKeyword() {
    return keyword;
  }
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
  
  public List<IvyApplication> getApplications() {
	return applications;
  }
  
  public void setApplications(List<IvyApplication> involvedApplications) {
	this.applications = involvedApplications;
  }
	   
  public boolean hasApplications() {
	return applications != null && !applications.isEmpty();
  }
  
}
