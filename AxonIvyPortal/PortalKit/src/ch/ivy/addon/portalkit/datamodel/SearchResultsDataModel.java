package ch.ivy.addon.portalkit.datamodel;

import java.io.Serializable;

public class SearchResultsDataModel implements Serializable {

  protected String keyword;
  protected TaskLazyDataModel taskDataModel;
  protected CaseLazyDataModel caseDataModel;
  
  public SearchResultsDataModel() {
    taskDataModel = new TaskLazyDataModel("search-results-tabview:task-results");
    caseDataModel = new CaseLazyDataModel("search-results-tabview:case-results");
  }
  
  public void search() {
    // Placeholder for customization
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
