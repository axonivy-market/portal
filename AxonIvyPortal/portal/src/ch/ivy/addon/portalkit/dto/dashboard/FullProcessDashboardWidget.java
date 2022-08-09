package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.enums.ProcessWidgetMode.FULL_MODE;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FullProcessDashboardWidget extends SingleProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  public FullProcessDashboardWidget(FullProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(FULL_MODE);
  }

  public FullProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(FULL_MODE);
  }

  public FullProcessDashboardWidget() {
    super();
    setDisplayMode(FULL_MODE);
  }
}
