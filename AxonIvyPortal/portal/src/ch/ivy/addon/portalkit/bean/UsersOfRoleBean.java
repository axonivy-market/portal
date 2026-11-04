package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class UsersOfRoleBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final int USER_LIMIT = 100;

  private String selectedRoleName;
  private String selectedRoleDisplayName;
  private String keyword = StringUtils.EMPTY;
  private List<UserDTO> memberNames = List.of();

  public void loadUsersOfRole(String roleName) {
    selectedRoleName = null;
    selectedRoleDisplayName = StringUtils.EMPTY;
    keyword = StringUtils.EMPTY;
    memberNames = List.of();
    if (StringUtils.isBlank(roleName) || ISecurityConstants.TOP_LEVEL_ROLE_NAME.equals(roleName)) {
      return;
    }
    Sudo.get(() -> {
      IRole role = ISecurityContext.current().roles().find(roleName);
      if (role != null) {
        selectedRoleName = role.getMemberName();
        selectedRoleDisplayName = role.getDisplayName();
      }
      return null;
    });
    queryUsers();
  }

  public void filterUserByName(String keyword) {
    this.keyword = StringUtils.defaultString(keyword);
    queryUsers();
  }

  private void queryUsers() {
    memberNames = List.of();
    if (selectedRoleName == null) {
      return;
    }
    Sudo.get(() -> {
      if (ISecurityContext.current().roles().find(selectedRoleName) != null) {
        SecurityService service = SecurityService.newInstance();
        memberNames = StringUtils.isEmpty(keyword)
            ? service.findAllUsersOfRoles(0, USER_LIMIT, List.of(selectedRoleName), null).getUsers()
            : service.findUsers(keyword, 0, USER_LIMIT, List.of(selectedRoleName), null).getUsers();
      }
      return null;
    });
  }

  public String getSelectedRoleName() {
    return selectedRoleName;
  }

  public String getSelectedRoleDisplayName() {
    return selectedRoleDisplayName;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public List<UserDTO> getMemberNames() {
    return memberNames;
  }

  public boolean isUserLimitReached() {
    return memberNames.size() >= USER_LIMIT;
  }
}
