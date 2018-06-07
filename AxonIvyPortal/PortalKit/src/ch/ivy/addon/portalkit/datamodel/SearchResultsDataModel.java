package ch.ivy.addon.portalkit.datamodel;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

public class SearchResultsDataModel implements Serializable {

  protected static final String CASE_WIDGET_COMPONENT_ID = "search-results-tabview:case-results";
  protected static final String TASK_WIDGET_COMPONENT_ID = "search-results-tabview:task-results";
  protected String keyword;
  protected TaskLazyDataModel taskDataModel;
  protected CaseLazyDataModel caseDataModel;
  
  public SearchResultsDataModel() {
    boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
    boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
    taskDataModel = initTaskDataModel();
    taskDataModel.setIgnoreInvolvedUser(hasReadAllTasksPermisson);
    taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
    
    caseDataModel = initCaseDataModel();
    caseDataModel.setIgnoreInvolvedUser(hasReadAllCasesPermission);
    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    if (serverId != null) {
      caseDataModel.setServerId(serverId);
    }
    String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
    if (StringUtils.isNotBlank(applicationName)) {
      caseDataModel.setInvolvedApplications(applicationName);
    }
  }
  
  public void search() {
    // Placeholder for customization
  }
  
  protected TaskLazyDataModel initTaskDataModel() {
    return new TaskLazyDataModel(TASK_WIDGET_COMPONENT_ID); 
  }
  
  protected CaseLazyDataModel initCaseDataModel() {
    return new CaseLazyDataModel(CASE_WIDGET_COMPONENT_ID);
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
    this.taskDataModel.getQueryCriteria().setKeyword(keyword);
    this.caseDataModel.getQueryCriteria().setKeyword(keyword);
  }

  public TaskLazyDataModel getTaskDataModel() {
    return taskDataModel;
  }

  public void setTaskDataModel(TaskLazyDataModel taskDataModel) {
    this.taskDataModel = taskDataModel;
  }

  public void setCaseDataModel(CaseLazyDataModel caseDataModel) {
    this.caseDataModel = caseDataModel;
  }
  
  public CaseLazyDataModel getCaseDataModel() {
    return caseDataModel;
  }
}
