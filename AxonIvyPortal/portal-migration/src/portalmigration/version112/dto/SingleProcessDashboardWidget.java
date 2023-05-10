package portalmigration.version112.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SingleProcessDashboardWidget extends ProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  private String processPath;

  public SingleProcessDashboardWidget(SingleProcessDashboardWidget widget) {
    super(widget);
  }

  public SingleProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
  }

  public SingleProcessDashboardWidget() {
    super();
  }

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }
}
