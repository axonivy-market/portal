package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.ICase;

public class ApplicationColumnModel extends CaseColumnModel implements Serializable {
  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/APPLICATION");
    this.field = DashboardStandardCaseColumn.APPLICATION.getField();
    this.style = defaultIfEmpty(this.style, TINY_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority u-text-align-center");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public Object display(ICase iCase) {
    if (iCase == null) {
      return null;
    }
    return iCase.getApplication().getName();
  }
  
  @JsonIgnore
  public List<String> getApplications() {
    return ListUtilities.transformList(IApplicationRepository.instance().allOf(ISecurityContext.current()), IApplication::getName);
  }
  
  @JsonIgnore
  public void setApplications(List<String> applications) {
    this.filterList = applications;
  }
  
  @JsonIgnore
  public List<String> getUserFilterApplicationOptions() {
    return getApplications();
  }
  
  @JsonIgnore
  public List<String> getUserFilterApplications() {
    return this.userFilterList;
  }
  
  @JsonIgnore
  public void setUserFilterApplications(List<String> applications) {
    this.userFilterList = applications;
  }
  
  @Override
  public Boolean getSortable() {
    return false;
  }
}
