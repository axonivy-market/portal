package ch.ivy.addon.portalkit.ivydata.bo;

public class IvySideStep {

  private String name;
  private String startLink;
  private boolean isAdhoc;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStartLink() {
    return startLink;
  }

  public void setStartLink(String startLink) {
    this.startLink = startLink;
  }

  public boolean isAdhoc() {
    return isAdhoc;
  }

  public void setAdhoc(boolean isAdhoc) {
    this.isAdhoc = isAdhoc;
  }

}
