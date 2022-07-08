package com.axonivy.portal.component.dto;

import java.io.Serializable;

import ch.ivyteam.ivy.security.ISecurityMember;

// Serializable is required to send system event when creating group chat in cluster mode
public class SecurityMemberDTO implements Serializable {

  private static final long serialVersionUID = 2141583280306161918L;
  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private boolean isUser;

  public SecurityMemberDTO() {}

  public SecurityMemberDTO(ISecurityMember securityMember) {
    this.id = securityMember.getId();
    this.name = securityMember.getName();
    this.displayName = securityMember.getDisplayName();
    this.memberName = securityMember.getMemberName();
    this.isUser = securityMember.isUser();
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

  public boolean isUser() {
    return isUser;
  }

  public void setUser(boolean isUser) {
    this.isUser = isUser;
  }

}
