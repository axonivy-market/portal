package ch.ivy.addon.portalkit.bo;

import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * Bean for remote web startable.
 *
 */
public class RemoteWebStartable implements IWebStartable {

  private String name;
  private String displayName;
  private String description;
  private String startLink;
  private String activatorDisplayName;
  private String activatorMemberName;

  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public ISecurityMember getActivator() {
    return null;
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

  @Override
  public WebLink getLink() {
    return null;
  }
}
