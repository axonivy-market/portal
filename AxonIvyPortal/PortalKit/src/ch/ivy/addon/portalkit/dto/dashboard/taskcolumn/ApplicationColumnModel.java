package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.ITask;

public class ApplicationColumnModel extends TaskColumnModel implements Serializable {
  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION");
    this.field = DashboardStandardTaskColumn.APPLICATION.getField();
    this.style = defaultIfEmpty(this.style, TINY_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority u-text-align-center");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION";
  }
  
  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getApplication().getName();
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
  public Boolean getDefaultSortable() {
    return false;
  }

}
