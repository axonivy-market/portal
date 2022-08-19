package com.axonivy.portal.components.ivydata.dto;

import java.util.List;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivyteam.ivy.security.IRole;

public class IvySecurityResultDTO extends AbstractResultDTO {

  private List<UserDTO> users;
  private List<IRole> roles;
  private List<SecurityMemberDTO> securityMembers;
  private List<RoleDTO> roleDTOs;
  
  public List<RoleDTO> getRoleDTOs() {
    return roleDTOs;
  }

  public void setRoleDTOs(List<RoleDTO> roleDTOs) {
    this.roleDTOs = roleDTOs;
  }

  public List<IRole> getRoles() {
    return roles;
  }

  public void setRoles(List<IRole> roles) {
    this.roles = roles;
  }

  public List<UserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }

  public List<SecurityMemberDTO> getSecurityMembers() {
    return securityMembers;
  }

  public void setSecurityMembers(List<SecurityMemberDTO> securityMembers) {
    this.securityMembers = securityMembers;
  }
}
