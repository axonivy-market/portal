package ch.ivy.addon.portalkit.bo;

public class Announcement {

  private String id;
  private String language;
  private String value;

  public Announcement() {}

  public Announcement(String language, String value) {
    this.language = language;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
