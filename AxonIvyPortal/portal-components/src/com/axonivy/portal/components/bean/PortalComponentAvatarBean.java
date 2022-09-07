package com.axonivy.portal.components.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.service.GlobalSettingService;
import com.axonivy.portal.components.util.SecurityMemberUtils;

import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
public class PortalComponentAvatarBean implements Serializable {

  private static final long serialVersionUID = 6793376941093725298L;

  public boolean getPortalShowAvatarSettingOrDefault(boolean defaultIfEmpty) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR, defaultIfEmpty);
  }

  public String getNameInitials(String displayName) {
    return SecurityMemberUtils.getNameInitials(displayName);
  }

  public String getEmailAddress(ISecurityMember securityMember) {
    if (securityMember == null || !securityMember.isUser()) {
      return "";
    }
    return ((IUser) securityMember).getEMailAddress();
  }

  public String getEmailAddress(SecurityMemberDTO securityMember) {
    if (securityMember == null || !securityMember.isUser()) {
      return "";
    }
    return securityMember.getEMailAddress();
  }

  public String getEmailAddress(UserDTO user) {
    if (user == null) {
      return "";
    }
    return user.getEmail();
  }

  @SuppressWarnings("unused")
  public String getEmailAddress(RoleDTO role) {
    return "";
  }

}
