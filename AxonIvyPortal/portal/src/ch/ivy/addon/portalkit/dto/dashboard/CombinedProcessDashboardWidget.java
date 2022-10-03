package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.enums.ProcessWidgetMode.COMBINED_MODE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.datamodel.DashboardProcessCaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.DashboardProcessTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CombinedProcessDashboardWidget extends SingleProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  private int rowsPerPage = 5;

  @JsonIgnore
  private DashboardProcessTaskLazyDataModel taskDataModel;
  @JsonIgnore
  private DashboardProcessCaseLazyDataModel caseDataModel;
  @JsonIgnore
  private boolean showCases;

  public CombinedProcessDashboardWidget(CombinedProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(COMBINED_MODE);
    rowsPerPage = widget.getRowsPerPage();
    taskDataModel = widget.getTaskDataModel();
    caseDataModel = widget.getCaseDataModel();
    showCases = widget.isShowCases();
    filterableColumns = widget.getFilterableColumns();
  }

  public CombinedProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(COMBINED_MODE);
  }

  public CombinedProcessDashboardWidget() {
    super();
    setDisplayMode(COMBINED_MODE);
  }

  public DashboardProcessTaskLazyDataModel getTaskDataModel() {
    return taskDataModel;
  }

  public DashboardProcessCaseLazyDataModel getCaseDataModel() {
    return caseDataModel;
  }

  @Override
  public void setProcess(DashboardProcess process) {
    super.setProcess(process);
    if (getProcess() != null) {
      this.taskDataModel =
          new DashboardProcessTaskLazyDataModel(getProcess().getProcessStartId(), getProcess().getName());
      this.caseDataModel =
          new DashboardProcessCaseLazyDataModel(getProcess().getProcessStartId(), getProcess().getName());
    }
  }

  public int getRowsPerPage() {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }

  public boolean isShowCases() {
    return showCases;
  }

  public void setShowCases(boolean showCases) {
    this.showCases = showCases;
  }

}
