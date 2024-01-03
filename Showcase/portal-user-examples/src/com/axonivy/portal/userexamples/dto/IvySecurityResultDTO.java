package com.axonivy.portal.userexamples.dto;

import java.util.List;

import ch.ivyteam.ivy.security.IRole;

public class IvySecurityResultDTO {

  private List<UserDTO> users;
  private List<IRole> roles;
  
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
}
