package ch.ivy.ws.addon.service;

import ch.ivy.ws.addon.enums.PortalCaseCustomVarField;

public class CaseCustomVarCharSearchCriteria {
  private PortalCaseCustomVarField portalCaseCustomVarField;
  private int limit;
  private String keyword;
  private String applicationIds;

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
 
  public String getApplicationIds() {
	return applicationIds;
  }
  
  public void setApplicationIds(String applicationIds) {
	this.applicationIds = applicationIds;
  }
	
  public boolean hasApplications() {
	return applicationIds != null && !applicationIds.isEmpty();
  }

}
