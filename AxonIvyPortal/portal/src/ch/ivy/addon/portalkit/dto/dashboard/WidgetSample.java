package ch.ivy.addon.portalkit.dto.dashboard;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class WidgetSample {

  private String name;
  private DashboardWidgetType type;
  private String image;
  private boolean useIcon;
  private String introduction;
  
  public WidgetSample(String name, DashboardWidgetType type, String image,String introduction) {
    this.name = name;
    this.type = type;
    this.image = image;
    this.introduction = introduction;
    this.useIcon = false;
  }
  
  public WidgetSample(String name, DashboardWidgetType type, String image, String introduction, boolean useIcon) {
      this.name = name;
      this.type = type;
      this.image = image;
      this.introduction = introduction;
      this.useIcon = useIcon;
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

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

public boolean isUseIcon() {
	return useIcon;
}

public void setUseIcon(boolean useIcon) {
	this.useIcon = useIcon;
}
}
