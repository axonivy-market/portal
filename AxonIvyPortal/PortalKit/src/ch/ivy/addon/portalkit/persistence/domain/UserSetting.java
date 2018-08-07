package ch.ivy.addon.portalkit.persistence.domain;

public class UserSetting extends BusinessEntity {
  private boolean defaultLinkACMAdded;
  private String userName;

  public boolean isDefaultLinkACMAdded() {
    return defaultLinkACMAdded;
  }

  public void setDefaultLinkACMAdded(boolean defaultLinkACMAdded) {
    this.defaultLinkACMAdded = defaultLinkACMAdded;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
