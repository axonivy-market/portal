package com.axonivy.portal.components.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;

public class RoleDTO {

  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private String parentName;


  public RoleDTO() {
    super();
  }

  public RoleDTO(IRole iRole) {
    id = iRole.getId();
    name = iRole.getName();
    displayName = iRole.getDisplayName();
    memberName = iRole.getMemberName();
    parentName = (iRole.getParent() == null) ? StringUtils.EMPTY : iRole.getParent().getDisplayName();

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
}
