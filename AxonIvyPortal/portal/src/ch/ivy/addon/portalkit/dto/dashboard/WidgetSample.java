package ch.ivy.addon.portalkit.dto.dashboard;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class WidgetSample {

  private String name;
  private DashboardWidgetType type;
  private String icon;
  private String introduction;
  
  public WidgetSample(String name, DashboardWidgetType type, String icon, String introduction) {
    this.name = name;
    this.type = type;
    this.icon = icon;
    this.introduction = introduction;
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }
}
