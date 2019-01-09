package ch.ivy.addon.portalkit.ivydata.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

public class IvySubstitute {

  private IRole substitionRole;
  private String substitionRoleDisplayName;
  private IUser substituteUser;
  private String description;

  public IRole getSubstitionRole() {
    return substitionRole;
  }

  public void setSubstitionRole(IRole substitionRole) {
    this.substitionRole = substitionRole;
  }

  public String getSubstitionRoleDisplayName() {
    if (StringUtils.isBlank(substitionRoleDisplayName) && substitionRole != null) {
      substitionRoleDisplayName = substitionRole.getDisplayName();
    }
    return substitionRoleDisplayName;
  }

  public void setSubstitionRoleDisplayName(String substitionRoleDisplayName) {
    this.substitionRoleDisplayName = substitionRoleDisplayName;
  }

  public IUser getSubstituteUser() {
    return substituteUser;
  }

  public void setSubstituteUser(IUser substituteUser) {
    this.substituteUser = substituteUser;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
