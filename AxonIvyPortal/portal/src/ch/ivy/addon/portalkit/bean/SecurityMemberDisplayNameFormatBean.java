package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ApplicationScoped
/**
 * This bean provide some methods to generate display name for {@link ISecurityMember}
 * and {@link UserDTO}
 */
public class SecurityMemberDisplayNameFormatBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String NOT_AVAILABLE_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable";

  public String generateBriefDisplayNameForSecurityMember(ISecurityMember member, String securityMemberName) {
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, securityMemberName);
  }
  
  public String generateFullDisplayNameForSecurityMember(ISecurityMember member, String securityMemberName) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForSecurityMember(member, securityMemberName);
  }
  
  public String generateFullDisplayNameForUserDTO(UserDTO user) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForUserDTO(user);
  }

  public boolean isDisplayNameNotAvailable(ISecurityMember member, String securityMemberName) {
    String generatedBriefDisplayName =  generateBriefDisplayNameForSecurityMember(member, securityMemberName);
    return Ivy.cms().co(NOT_AVAILABLE_CMS).equals(generatedBriefDisplayName);
  }

  public String joinSecurityMemberNames(List<ISecurityMember> securityMembers) {
    return SecurityMemberDisplayNameUtils.joinSecurityMemberNames(securityMembers);
  }
}
