package com.axonivy.portal.component.dto;

import java.io.Serializable;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

// Serializable is required to send system event when creating group chat in cluster mode
public class SecurityMemberDTO implements Serializable {

  private static final long serialVersionUID = 5152412894300680832L;
  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private String eMailAddress;
  private boolean isUser;
  private boolean isEnabled;

  public SecurityMemberDTO() {}

  public SecurityMemberDTO(ISecurityMember securityMember) {
    this.id = securityMember.getId();
    this.name = securityMember.getName();
    this.displayName = securityMember.getDisplayName();
    this.memberName = securityMember.getMemberName();
    this.isUser = securityMember.isUser();
    if(securityMember.isUser()) {
      IUser user = (IUser) securityMember;
      this.isEnabled = user.isEnabled();
      this.eMailAddress = user.getEMailAddress();
    }
    else {
      this.isEnabled = true;
    }
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

  public String getEMailAddress() {
    return eMailAddress;
  }

  public void setEMailAddress(String eMailAddress) {
    this.eMailAddress = eMailAddress;
  }

  public boolean isUser() {
    return isUser;
  }

  public void setUser(boolean isUser) {
    this.isUser = isUser;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public String getBriefDisplayNameWithState() {
    if (this.isEnabled) {
      return this.displayName;
    }
    return Ivy.cms().co("/Labels/disabledUserPrefix") + " " + this.displayName;
  }
}
