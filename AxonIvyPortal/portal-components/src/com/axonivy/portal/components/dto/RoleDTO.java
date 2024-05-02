package com.axonivy.portal.components.dto;

import ch.ivyteam.ivy.security.IRole;

public class RoleDTO {
  @Deprecated(forRemoval = true, since = "11.2.0")
  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private String securityMemberId;

  public RoleDTO() {
    super();
  }

  public RoleDTO(IRole iRole) {
    this.id = iRole.getId();
    this.name = iRole.getName();
    this.displayName = iRole.getDisplayName();
    this.memberName = iRole.getMemberName();
    this.securityMemberId = iRole.getSecurityMemberId();
  }
  
  /**
   * @deprecated use {@link #getSecurityMemberId()}
   * @return id
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public long getId() {
    return id;
  }

  /**
   * @deprecated use {@link #setSecurityMemberId(String)}
   * @param id
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
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

  public String getSecurityMemberId() {
    return securityMemberId;
  }

  public void setSecurityMemberId(String securityMemberId) {
    this.securityMemberId = securityMemberId;
  }
}
