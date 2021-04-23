package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ViewScoped
public class SecurityMemberDisplayNameBean implements Serializable {

  private static final long serialVersionUID = 4105979446859094503L;
  private List<UserDTO> memberNames;
  private String roleName;
  private boolean isShowAllUser;
  private boolean isRenderUserOfRole;
  private String usersInTooltips;
  private String keyword;

  @PostConstruct
  public void init() {
    memberNames = new ArrayList<>();
    GlobalSettingService service = new GlobalSettingService();
    String showAllUserConfig = service.findGlobalSettingValue(GlobalVariable.DISPLAY_USERS_OF_ROLE.name());
    this.isShowAllUser = BooleanUtils.toBoolean(showAllUserConfig);
  }

  public void preRender(ISecurityMember securityMember) {
    this.isRenderUserOfRole = this.isShowAllUser && securityMember != null && !securityMember.isUser();
  }

  public void loadUsersOfRole(String roleName) {
    this.roleName = roleName;
    this.memberNames = new ArrayList<>();
    IvySecurityResultDTO result =
        SecurityService.newInstance().findAllUsersOfRoles(0, 101, Arrays.asList(roleName), null);
    this.memberNames = result.getUsers();

    if (this.memberNames.size() >= 100) {
      UserDTO user = new UserDTO();
      user.setDisplayName(Ivy.cms()
          .co("/ch.ivy.addon.portalkit.ui.jsf/components/SecurityMemberDisplayName/filterToSee").concat(" ..."));
      this.memberNames.add(user);
    }
  }

  public void filterUserByName(String userName) {
    IvySecurityResultDTO result =
        SecurityService.newInstance().findUsers(userName, 0, 101, Arrays.asList(this.roleName), null);
    this.memberNames = result.getUsers();
  }

  public void loadUsersForTooltip(ISecurityMember securityMember) {
    if (securityMember != null && !securityMember.isUser() && this.isShowAllUser) {
      this.roleName = securityMember.getName();
      this.usersInTooltips = SecurityMemberUtils.buildTooltipFromUsers(this.roleName);
    }
  }

  public String getBriefDisplayName(ISecurityMember member, String securityMemberName) {
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, securityMemberName);
  }
  
  public String getFullDisplayName(ISecurityMember member, String securityMemberName) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForSecurityMember(member, securityMemberName);
  }
  
  public String getFullDisplayName(UserDTO user) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForUserDTO(user);
  }
  
  public List<UserDTO> getMemberNames() {
    return memberNames;
  }

  public void setMemberNames(List<UserDTO> memberNames) {
    this.memberNames = memberNames;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public boolean isShowAllUser() {
    return isShowAllUser;
  }

  public void setShowAllUser(boolean isShowAllUser) {
    this.isShowAllUser = isShowAllUser;
  }

  public boolean isRenderUserOfRole() {
    return isRenderUserOfRole;
  }

  public void setRenderUserOfRole(boolean isRenderUserOfRole) {
    this.isRenderUserOfRole = isRenderUserOfRole;
  }

  public String getUsersInTooltips() {
    return usersInTooltips;
  }

  public void setUsersInTooltips(String usersInTooltips) {
    this.usersInTooltips = usersInTooltips;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

}
