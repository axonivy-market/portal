package ch.ivy.addon.portalkit.bo;

import java.util.List;

public abstract class ColumnsConfiguration {
  private Long serverId;
  private Long applicationId;
  private Long userId;
  private List<String> selectedColumns;
  private boolean isAutoHideColumns;
  private Long processModelId;
  
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public List<String> getSelectedColumns() {
    return selectedColumns;
  }
  public void setSelectedColumns(List<String> selectedColumns) {
    this.selectedColumns = selectedColumns;
  }
  public boolean isAutoHideColumns() {
    return isAutoHideColumns;
  }
  public void setAutoHideColumns(boolean isAutoHideColumns) {
    this.isAutoHideColumns = isAutoHideColumns;
  }
  public Long getServerId() {
    return serverId;
  }
  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }
  public Long getApplicationId() {
    return applicationId;
  }
  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }
  public Long getProcessModelId() {
    return processModelId;
  }
  public void setProcessModelId(Long processModelId) {
    this.processModelId = processModelId;
  }
}
