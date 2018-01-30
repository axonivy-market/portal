package ch.ivy.addon.portalkit.bo;


/**
 * Bean for remote web startable.
 *
 */
public class RemoteWebStartable {

  private String name;
  private String displayName;
  private String description;
  private String startLink;
  private String activatorDisplayName;
  private String activatorMemberName;

  public String getName() {
    return name;
  }
  
  public String getDisplayName() {
    return displayName;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getActivatorDisplayName() {
    return activatorDisplayName;
  }

  public void setActivatorDisplayName(String activatorDisplayName) {
    this.activatorDisplayName = activatorDisplayName;
  }

  public String getActivatorMemberName() {
    return activatorMemberName;
  }

  public void setActivatorMemberName(String activatorMemberName) {
    this.activatorMemberName = activatorMemberName;
  }

  public String getStartLink() {
    return startLink;
  }

  public void setStartLink(String startLink) {
    this.startLink = startLink;
  }

}
