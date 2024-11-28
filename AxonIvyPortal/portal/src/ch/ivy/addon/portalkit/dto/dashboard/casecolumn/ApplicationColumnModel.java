package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.ICase;

public class ApplicationColumnModel extends CaseColumnModel implements Serializable {
  private static final long serialVersionUID = 6708953330031478257L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.APPLICATION.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority u-text-align-center");
    this.format = DashboardColumnFormat.CUSTOM;
    this.quickSearch = defaultIfEmpty(this.quickSearch, false);
  }
  
  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/APPLICATION";
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
    return ListUtilities.transformList(IApplicationRepository.of(ISecurityContext.current()).all(), IApplication::getName);
  }
  
  @JsonIgnore
  public void setApplications(List<String> applications) {
    this.filterList = applications;
  }
  
  @Override
  public Boolean getDefaultSortable() {
    return false;
  }
  
  public void updateApplications(@SuppressWarnings("unused") CaseDashboardWidget widget) {
  }
  public void initializeApplications(@SuppressWarnings("unused") CaseDashboardWidget widget) {
  }
  
  @Override
  public List<String> getUserFilterListOptions(){
    if (CollectionUtils.isEmpty(this.userFilterListOptions)) {
      this.userFilterListOptions = getApplications();
    }
    
    return this.userFilterListOptions;
  }
  
  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
