package portalmigration.version112.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CombinedProcessDashboardWidget extends SingleProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  private int rowsPerPage = 5;

  @JsonIgnore
  private boolean showCases;

  public CombinedProcessDashboardWidget(CombinedProcessDashboardWidget widget) {
    super(widget);
    rowsPerPage = widget.getRowsPerPage();
    showCases = widget.isShowCases();
  }

  public CombinedProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
  }

  public CombinedProcessDashboardWidget() {
    super();
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
