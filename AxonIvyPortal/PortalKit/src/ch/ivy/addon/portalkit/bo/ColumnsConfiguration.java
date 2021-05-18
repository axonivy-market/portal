package ch.ivy.addon.portalkit.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class ColumnsConfiguration extends AbstractConfiguration {

  private List<String> selectedColumns;
  private boolean isAutoHideColumns;
  private Long processModelId;

  public ColumnsConfiguration() {
    setIsPublic(false);
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

  public Long getProcessModelId() {
    return processModelId;
  }

  public void setProcessModelId(Long processModelId) {
    this.processModelId = processModelId;
  }
}
