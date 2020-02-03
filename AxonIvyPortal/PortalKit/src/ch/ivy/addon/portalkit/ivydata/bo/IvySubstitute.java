package ch.ivy.addon.portalkit.ivydata.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.SubstitutionType;

public class IvySubstitute {

  private IRole substitionRole;
  private String substitionRoleDisplayName;
  private UserDTO substituteUser;
  private String description;
  private SubstitutionType substitutionType;
  private UserDTO ownerUser;

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

  public SubstitutionType getSubstitutionType() {
    return substitutionType;
  }

  public void setSubstitutionType(SubstitutionType substitutionType) {
    this.substitutionType = substitutionType;
  }

  public UserDTO getOwnerUser() {
    return ownerUser;
  }

  public void setOwnerUser(UserDTO ownerUser) {
    this.ownerUser = ownerUser;
  }

}
