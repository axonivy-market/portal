package ch.ivy.addon.portalkit.bo;

public class AnnouncementStatus {

  private String id;
  private String enabled;

  public AnnouncementStatus() {}

  public AnnouncementStatus(String enabled) {
    this.enabled = enabled;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEnabled() {
    return enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }

}
