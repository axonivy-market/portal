package ch.ivy.addon.portalkit.ivydata.bo;

import org.apache.commons.lang3.StringUtils;

public class IvyApplication {

  private String name;
  private String displayName;
  private boolean isActive;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    if (StringUtils.isBlank(displayName)) {
      return getName();
    }
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }
}
