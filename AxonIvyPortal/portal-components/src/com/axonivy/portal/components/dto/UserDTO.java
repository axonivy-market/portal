package com.axonivy.portal.components.dto;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Record;
import ch.ivyteam.ivy.security.IUser;

public class UserDTO {

  private long id;
  private String name;
  private String displayName;
  private String memberName;
  private String email;
  private boolean isEnabled;
  private List<RoleDTO> roles;
  
  public UserDTO() {}

  public UserDTO(IUser user) {
    this.name = user.getName();
    this.memberName = user.getMemberName();
    this.displayName = user.getDisplayName();
    this.email = user.getEMailAddress();
    this.id = user.getId();
    this.isEnabled = user.isEnabled();
  }
  
  public static UserDTO newUserWithRoles(IUser iUser) {
    UserDTO user = new UserDTO(iUser);
    user.setRoles(iUser.getAllRoles().stream().map(RoleDTO::new).collect(Collectors.toList()));
    return user;
  }

  public UserDTO(Record record) {
    this.id = Long.valueOf(record.getField("USERID").toString());
    this.name = record.getField("NAME").toString();
    this.memberName = "#" + this.name;
    this.displayName = record.getField("FULLNAME").toString();
    this.isEnabled = Integer.valueOf(record.getField("STATE").toString()) == 0;
  }
  
  public UserDTO(UserDTO user) {
    this.name = user.getName();
    this.memberName = user.getMemberName();
    this.displayName = user.getDisplayName();
    this.id = user.getId();
    this.isEnabled = user.isEnabled();
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

  public long getId() {
    return id;
  }

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

  public List<RoleDTO> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDTO> roles) {
    this.roles = roles;
  }
}
