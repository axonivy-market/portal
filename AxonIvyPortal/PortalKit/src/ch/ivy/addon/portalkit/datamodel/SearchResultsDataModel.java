package ch.ivy.addon.portalkit.datamodel;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.util.PermissionUtils;

/**
 * Lazy data model for search. Only override method which is mentioned in Portal document
 *
 */
public class SearchResultsDataModel implements Serializable {

  private static final long serialVersionUID = -472756089365264117L;
  
  protected static final String CASE_WIDGET_COMPONENT_ID = "search-results-tabview:case-results";
  protected static final String TASK_WIDGET_COMPONENT_ID = "search-results-tabview:task-results";
  protected String keyword;
  protected TaskLazyDataModel taskDataModel;
  protected CaseLazyDataModel caseDataModel;

  /**
   * @hidden
   */
  public SearchResultsDataModel() {
    boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
    boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
    taskDataModel = initTaskDataModel();
    taskDataModel.setAdminQuery(hasReadAllTasksPermisson);
    taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

    caseDataModel = initCaseDataModel();
    caseDataModel.setIsAdminQuery(hasReadAllCasesPermission);
  }

  /**
   * Implement your custom search function by override this function
   */
  public void search() {
    // Placeholder for customization
  }

  protected TaskLazyDataModel initTaskDataModel() {
    return new TaskLazyDataModel(TASK_WIDGET_COMPONENT_ID);
  }

  protected CaseLazyDataModel initCaseDataModel() {
    return new CaseLazyDataModel(CASE_WIDGET_COMPONENT_ID);
  }

  /**
   * @hidden
   * @return keyword
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * @hidden
   * @param keyword
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword;
    this.taskDataModel.getCriteria().setKeyword(keyword);
    this.caseDataModel.getCriteria().setKeyword(keyword);
  }

  /**
   * @hidden
   * @return taskDataModel
   */
  public TaskLazyDataModel getTaskDataModel() {
    return taskDataModel;
  }

  /**
   * @hidden
   * @param taskDataModel
   */
  public void setTaskDataModel(TaskLazyDataModel taskDataModel) {
    this.taskDataModel = taskDataModel;
  }

  /**
   * @hidden
   * @param caseDataModel
   */
  public void setCaseDataModel(CaseLazyDataModel caseDataModel) {
    this.caseDataModel = caseDataModel;
  }

  /**
   * @hidden
   * @return caseDataModel
   */
  public CaseLazyDataModel getCaseDataModel() {
    return caseDataModel;
  }
}
