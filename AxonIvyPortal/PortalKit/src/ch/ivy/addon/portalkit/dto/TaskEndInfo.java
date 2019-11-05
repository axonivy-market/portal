package ch.ivy.addon.portalkit.dto;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.NavigationHistory;
import ch.ivy.addon.portalkit.enums.PortalPage;

public class TaskEndInfo {

  private TaskLazyDataModel dataModel;
  private NavigationHistory navigationHistory;
  private PortalPage portalPage;
  private Boolean isStartedInTaskDetails;
  private Boolean isInIFrame;

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public PortalPage getPortalPage() {
    return portalPage;
  }

  public void setPortalPage(PortalPage portalPage) {
    this.portalPage = portalPage;
  }

  public NavigationHistory getNavigationHistory() {
    return navigationHistory;
  }

  public void setNavigationHistory(NavigationHistory navigationHistory) {
    this.navigationHistory = navigationHistory;
  }

  public Boolean getIsStartedInTaskDetails() {
    return isStartedInTaskDetails;
  }

  public void setIsStartedInTaskDetails(Boolean isStartedInTaskDetails) {
    this.isStartedInTaskDetails = isStartedInTaskDetails;
  }
  
  public Boolean getIsInIFrame() {
    return isInIFrame;
  }
  
  public void setIsInIFrame(Boolean isInIFrame) {
    this.isInIFrame = isInIFrame;
  }
}
