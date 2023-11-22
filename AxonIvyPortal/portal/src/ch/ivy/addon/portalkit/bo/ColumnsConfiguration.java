package ch.ivy.addon.portalkit.bo;

import java.util.List;

public abstract class ColumnsConfiguration {

  private Long applicationId;
  @Deprecated(forRemoval = true, since = "11.2.0")
  private Long userId;
  private List<String> selectedColumns;
  private boolean isAutoHideColumns;
  private Long processModelId;
  private String securityMemberId;

  /**
   * @deprecated use {@link #getSecurityMemberId()}
   * @return id
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public Long getUserId() {
    return userId;
  }

  /**
   * @deprecated use {@link #setSecurityMemberId(String)}
   * @param userId
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
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

  public String getSecurityMemberId() {
    return securityMemberId;
  }

  public void setSecurityMemberId(String securityMemberId) {
    this.securityMemberId = securityMemberId;
  }
}
