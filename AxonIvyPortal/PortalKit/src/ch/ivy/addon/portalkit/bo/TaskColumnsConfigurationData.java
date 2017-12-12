package ch.ivy.addon.portalkit.bo;

import java.util.List;

public class TaskColumnsConfigurationData {
  
  private Long userId;
  private List<String> selectedColumns;
  private boolean isAutoHideColumns;
  
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
  
}
