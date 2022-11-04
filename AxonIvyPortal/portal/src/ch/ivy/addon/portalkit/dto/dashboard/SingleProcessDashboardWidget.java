package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SingleProcessDashboardWidget extends ProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  private String processPath;

  @JsonIgnore
  private DashboardProcess process;
  @JsonIgnore
  private boolean hasPermissionToSee;

  public SingleProcessDashboardWidget(SingleProcessDashboardWidget widget) {
    super(widget);
    processPath = widget.getProcessPath();
    process = widget.getProcess();
    filterableColumns = widget.getFilterableColumns();
  }

  public SingleProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    filterableColumns = widget.getFilterableColumns();
  }

  public SingleProcessDashboardWidget() {
    super();
    filterableColumns = new ArrayList<>();
  }

  public DashboardProcess getProcess() {
    return process;
  }

  public void setProcess(DashboardProcess process) {
    this.process = process;
  }

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  public boolean isHasPermissionToSee() {
    return hasPermissionToSee;
  }

  public void setHasPermissionToSee(boolean hasPermissionToSee) {
    this.hasPermissionToSee = hasPermissionToSee;
  }
  
}
