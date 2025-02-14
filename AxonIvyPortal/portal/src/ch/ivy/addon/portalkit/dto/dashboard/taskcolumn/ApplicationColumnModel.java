package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.ITask;

public class ApplicationColumnModel extends TaskColumnModel implements Serializable {
  private static final long serialVersionUID = 8918150285605338212L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.APPLICATION.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority text-center widget-column");
    this.format = DashboardColumnFormat.CUSTOM;
    this.quickSearch = defaultIfEmpty(this.quickSearch, false);
  }
  
  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION";
  }
  
  @Override
  protected int getDefaultColumnWidth() {
    return TINY_WIDTH;
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

  public void updateApplications(@SuppressWarnings("unused") TaskDashboardWidget  widget) {
  }
  public void initializeApplications(@SuppressWarnings("unused") TaskDashboardWidget widget) {
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
