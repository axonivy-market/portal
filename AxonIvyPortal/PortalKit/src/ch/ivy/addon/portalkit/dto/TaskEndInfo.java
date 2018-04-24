package ch.ivy.addon.portalkit.dto;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

public class TaskEndInfo {

  private TaskLazyDataModel dataModel;

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


}
