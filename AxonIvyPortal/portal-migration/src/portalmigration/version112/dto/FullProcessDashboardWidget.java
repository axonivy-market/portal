package portalmigration.version112.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FullProcessDashboardWidget extends SingleProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  public FullProcessDashboardWidget(FullProcessDashboardWidget widget) {
    super(widget);
  }

  public FullProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
  }

  public FullProcessDashboardWidget() {
    super();
  }
}
