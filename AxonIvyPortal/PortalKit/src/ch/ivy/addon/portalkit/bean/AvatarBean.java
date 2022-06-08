package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
public class AvatarBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public boolean isShowAvatar() {
    return new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR);
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
