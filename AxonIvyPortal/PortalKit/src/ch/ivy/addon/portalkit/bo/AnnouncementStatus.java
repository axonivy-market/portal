package ch.ivy.addon.portalkit.bo;

public class AnnouncementStatus {

  private String id;
  private String value;

  public AnnouncementStatus() {}

  public AnnouncementStatus(String value) {
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
