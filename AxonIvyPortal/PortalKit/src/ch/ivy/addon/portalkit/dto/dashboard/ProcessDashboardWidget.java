package ch.ivy.addon.portalkit.dto.dashboard;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class ProcessDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  public ProcessDashboardWidget() {
    this.setType(DashboardWidgetType.PROCESS);
  }

}
