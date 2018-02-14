package ch.ivy.addon.portalkit.dto;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

public class TaskEndInfo {

  private TaskLazyDataModel dataModel;
  private boolean isPortalHomeLastPage;

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public boolean isPortalHomeLastPage() {
    return isPortalHomeLastPage;
  }

  public void setPortalHomeLastPage(boolean isPortalHomeLastPage) {
    this.isPortalHomeLastPage = isPortalHomeLastPage;
  }

}
