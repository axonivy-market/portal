package com.axonivy.portal.components.dto;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class UserDTO {

  @Deprecated(forRemoval = true, since = "11.2.0")
  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private String email;
  private boolean isEnabled;
  private String securityMemberId;
  
  public UserDTO() {}

  public UserDTO(IUser user) {
    this.name = user.getName();
    this.memberName = user.getMemberName();
    this.displayName = user.getDisplayName();
    this.email = user.getEMailAddress();
    this.id = user.getId();
    this.isEnabled = user.isEnabled();
    this.securityMemberId = user.getSecurityMemberId();
  }
  
  public UserDTO(UserDTO user) {
    this.name = user.getName();
    this.memberName = user.getMemberName();
    this.displayName = user.getDisplayName();
    this.id = user.getId();
    this.isEnabled = user.isEnabled();
    this.securityMemberId = user.getSecurityMemberId();
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @deprecated use {@link #getSecurityMemberId()}
   * @return userId
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public long getId() {
    return id;
  }

  /**
   * {@link Deprecated} use {@link #setSecurityMemberId(String)}
   * @param id
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public void setId(long id) {
    this.id = id;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  public String getBriefDisplayNameWithState() {
    if(this.isEnabled) {
      return this.displayName;
    }
    return Ivy.cms().co("/Labels/disabledUserPrefix") + " " + this.displayName;
  }

  public String getSecurityMemberId() {
    return securityMemberId;
  }

  public void setSecurityMemberId(String securityMemberId) {
    this.securityMemberId = securityMemberId;
  }
}
