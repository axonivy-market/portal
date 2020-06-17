package ch.ivy.addon.portalkit.dto;

import java.util.List;

public class DashboardWidgetConfiguration {

  private String user;
  private List<DashboardWidget> widgets;
  
  public DashboardWidgetConfiguration() {
  }
  
  public String getUser() {
    return user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public List<DashboardWidget> getWidgets() {
    return widgets;
  }
  
  public void setWidgets(List<DashboardWidget> widgets) {
    this.widgets = widgets;
  }
}
