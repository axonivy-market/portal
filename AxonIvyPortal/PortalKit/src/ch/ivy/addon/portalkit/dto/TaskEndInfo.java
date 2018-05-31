package ch.ivy.addon.portalkit.dto;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.NavigationHistory;

public class TaskEndInfo {

  private TaskLazyDataModel dataModel;


  private NavigationHistory navigationHistory;

  private Boolean isFromPortalHome;

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public Boolean getIsFromPortalHome() {
    return isFromPortalHome;
  }

  public void setIsFromPortalHome(Boolean isFromPortalHome) {
    this.isFromPortalHome = isFromPortalHome;
  }

  public NavigationHistory getNavigationHistory() {
    return navigationHistory;
  }

  public void setNavigationHistory(NavigationHistory navigationHistory) {
    this.navigationHistory = navigationHistory;
  }

}
