package ch.ivy.addon.portalkit.ivydata.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.security.IRole;

public class IvySubstitute {

  private IRole substitionRole;
  private String substitionRoleDisplayName;
  private UserDTO substituteUser;
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

  public UserDTO getSubstituteUser() {
    return substituteUser;
  }

  public void setSubstituteUser(UserDTO substituteUser) {
    this.substituteUser = substituteUser;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
