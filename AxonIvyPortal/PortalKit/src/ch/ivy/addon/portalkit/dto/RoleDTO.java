package ch.ivy.addon.portalkit.dto;

import ch.ivyteam.ivy.security.IRole;

/**
 * @deprecated use RoleDTO in package com.axonivy.portal.component.dto
 *
 */
@Deprecated(since="8.0.27")
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
