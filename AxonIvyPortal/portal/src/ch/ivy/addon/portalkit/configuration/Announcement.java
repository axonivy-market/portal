package ch.ivy.addon.portalkit.configuration;

import java.util.List;

public class Announcement {

  private String version;
  private List<LocalizationContent> contents;
  private boolean enabled;

  public Announcement() {
  }

  public Announcement(List<LocalizationContent> contents, boolean enabled) {
    super();
    this.contents = contents;
    this.enabled = enabled;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public List<LocalizationContent> getContents() {
    return contents;
  }

  public void setContents(List<LocalizationContent> contents) {
    this.contents = contents;
  }

}
