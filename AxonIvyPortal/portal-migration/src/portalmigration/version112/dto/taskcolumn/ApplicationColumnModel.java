package portalmigration.version112.dto.taskcolumn;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.ITask;
import portalmigration.version112.dto.TaskDashboardWidget;
import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardStandardTaskColumn;
import portalmigration.version112.util.ListUtilities;

public class ApplicationColumnModel extends TaskColumnModel implements Serializable {
  private static final long serialVersionUID = 8918150285605338212L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
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
}
