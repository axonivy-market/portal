package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ApplicationScoped
/**
 * This bean provide some methods to generate display name for {@link ISecurityMember} and {@link UserDTO}
 */
public class SecurityMemberDisplayNameFormatBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String NOT_AVAILABLE_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable";

  private boolean isDisplayGlobalCreator;
  private String globalCreatorName;

  public void init() {
    this.globalCreatorName = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.CASE_CREATOR);
    this.isDisplayGlobalCreator = !StringUtils.isEmpty(globalCreatorName);
    Ivy.log().error(globalCreatorName);
    Ivy.log().error(isDisplayGlobalCreator);

  }

  public String generateBriefDisplayNameForSecurityMember(ISecurityMember member, String securityMemberName) {
    Ivy.log().error("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    if (isDisplayGlobalCreator) {
      return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(null, globalCreatorName);
    } else {
      return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, securityMemberName);
    }
  }

  public String generateFullDisplayNameForSecurityMember(ISecurityMember member, String securityMemberName) {
    if (isDisplayGlobalCreator) {
      return SecurityMemberDisplayNameUtils.generateFullDisplayNameForSecurityMember(null, globalCreatorName);
    } else {
      return SecurityMemberDisplayNameUtils.generateFullDisplayNameForSecurityMember(member, securityMemberName);
    }
  }

  public String generateFullDisplayNameForUserDTO(UserDTO user) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForUserDTO(user);
  }

  public boolean isDisplayNameNotAvailable(ISecurityMember member, String securityMemberName) {
    String generatedBriefDisplayName = generateBriefDisplayNameForSecurityMember(member, securityMemberName);
    return Ivy.cms().co(NOT_AVAILABLE_CMS).equals(generatedBriefDisplayName);
  }

  public String joinSecurityMemberNames(List<ISecurityMember> securityMembers) {
    return SecurityMemberDisplayNameUtils.joinSecurityMemberNames(securityMembers);
  }
}
