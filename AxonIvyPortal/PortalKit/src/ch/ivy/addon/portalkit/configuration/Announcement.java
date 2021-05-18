package ch.ivy.addon.portalkit.configuration;

import java.util.List;

public class Announcement {

  private List<LocalizationContent> contents;
  private boolean enabled;

  public Announcement() {
  }

  public Announcement(List<LocalizationContent> contents, boolean enabled) {
    super();
    this.contents = contents;
    this.enabled = enabled;
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
