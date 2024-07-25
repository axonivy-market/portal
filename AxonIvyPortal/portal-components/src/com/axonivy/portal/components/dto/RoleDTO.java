package com.axonivy.portal.components.dto;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.security.IRole;

public class RoleDTO {

  private long id;
  private String name;
  private String displayName;
  private String memberName;

  public RoleDTO() {
    super();
  }

  public RoleDTO(IRole iRole) {
    id = iRole.getId();
    name = iRole.getName();
    displayName = iRole.getDisplayName();
    memberName = iRole.getMemberName();
  }
  
  public static List<RoleDTO> toRoles(List<IRole> iroles) {
    return iroles.stream().map(RoleDTO::new).collect(Collectors.toList());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }
}
