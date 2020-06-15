package ch.ivy.addon.portalkit.dto;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class WidgetSample {

  private String name;
  private DashboardWidgetType type;
  private String image;
  
  public WidgetSample(String name, DashboardWidgetType type, String image) {
    this.name = name;
    this.type = type;
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DashboardWidgetType getType() {
    return type;
  }

  public void setType(DashboardWidgetType type) {
    this.type = type;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
  
}
