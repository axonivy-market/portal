package com.axonivy.portal.components.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;

public class RoleDTO {
  @Deprecated(forRemoval = true, since = "11.2.0")
  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private String parentName;
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
    parentName = (iRole.getParent() == null) ? StringUtils.EMPTY : iRole.getParent().getDisplayName();

  }
  
  public static List<RoleDTO> toRoles(List<IRole> iroles) {
    return iroles.stream().map(RoleDTO::new).collect(Collectors.toList());
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

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public String getRoleWithParentName() {
    return String.format("%s (%s: %s)", getDisplayName(), Ivy.cms().co("/Labels/ParentRole"),
        getParentName().isEmpty() ? Ivy.cms().co("/Labels/None") : getParentName());
  }

  public String getSecurityMemberId() {
    return securityMemberId;
  }

  public void setSecurityMemberId(String securityMemberId) {
    this.securityMemberId = securityMemberId;
  }
}
