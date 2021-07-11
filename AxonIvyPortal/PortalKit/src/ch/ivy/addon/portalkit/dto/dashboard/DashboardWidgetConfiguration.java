package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.List;

public class DashboardWidgetConfiguration {

  private List<DashboardWidget> widgets;
  
  public DashboardWidgetConfiguration() {
  }
  
  public List<DashboardWidget> getWidgets() {
    return widgets;
  }
  
  public void setWidgets(List<DashboardWidget> widgets) {
    this.widgets = widgets;
  }
}
