package ch.ivy.addon.portalkit.bean;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.role.RoleHolder;
import ch.ivy.addon.portalkit.role.RoleTreeDataModel;
import ch.ivy.addon.portalkit.role.UserAssignedDataModel;
import ch.ivy.addon.portalkit.role.UserHolder;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.RoleUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.role.NewRole;

@ManagedBean
@ViewScoped
public class RoleManagementBean implements Serializable {
  private static final long serialVersionUID = -4867516739222351669L;

  private IRole roleTopLevel;
  private RoleTreeDataModel roleTreeModel;
  private UserAssignedDataModel userAssignmentModel;
  private RoleHolder selectedRole;
  private RoleDTO selectedParentRole;
  private UserDTO selectedUser;

  private boolean canCreateRole;
  private boolean canDeleteRole;
  private boolean canMoveRole;
  private boolean isCreationMode;
  private boolean canModifyRoleInformation;
  private boolean canModifyUserAssignment;

  public void initRoleManagement() {
    roleTopLevel = ISecurityContext.current().roles().topLevel();
    roleTreeModel = new RoleTreeDataModel();
    roleTreeModel.reloadTree();
    initPermission();
  }

  private void initPermission() {
    canCreateRole = PermissionUtils.hasPermission(IPermission.ROLE_CREATE);
    canDeleteRole = PermissionUtils.hasPermission(IPermission.ROLE_DELETE);
    canMoveRole = PermissionUtils.hasPermission(IPermission.ROLE_MOVE);
  }

  public void prepareAddingNewRole() {
    setCreationMode(true);
    setCanModifyRoleInformation(true);
    setCanModifyUserAssignment(true);
    setSelectedUser(null);
    setSelectedRole(new RoleHolder());
    setSelectedParentRole(new RoleDTO(roleTopLevel));
    this.userAssignmentModel = new UserAssignedDataModel(true);
  }

  public void setDataForEditRole(RoleHolder role) {
    if (nonNull(role)) {
      setCreationMode(false);
      setCanModifyRoleInformation(true);
      setCanModifyUserAssignment(false);
      var foundRole = RoleUtils.findRole(role.getName());
      if (nonNull(foundRole)) {
        setSelectedUser(null);
        setSelectedRole(role);
        this.userAssignmentModel = new UserAssignedDataModel(role.getName());
      }
    }
  }

  public void setDataForAssignUserToRole(RoleHolder role) {
    if (nonNull(role)) {
      setDataForEditRole(role);
      setCanModifyRoleInformation(false);
      setCanModifyUserAssignment(true);
    }
  }

  public void assignUserToRole() {
    if (nonNull(selectedUser)) {
      if (isCreationMode) {
        userAssignmentModel.assignUserToHolderList(true, new UserHolder(selectedUser));
      } else {
        addOrRemoveRoleForUser(RoleUtils.findRole(selectedRole.getName()), selectedUser.getName(), false);
        userAssignmentModel.getExcludedUsernames().add(selectedUser.getName());
      }
      selectedUser = null;
    }
  }

  public void closeUserAssignmentSection() {
    setSelectedUser(null);
    this.roleTreeModel.reloadTree();
  }

  public void removeUserOutOfRole(UserHolder user) {
    if (nonNull(user)) {
      if (isCreationMode) {
        userAssignmentModel.removeUserOutOfHolderList(user);
      } else {
        addOrRemoveRoleForUser(RoleUtils.findRole(selectedRole.getName()), user.getName(), true);
      }
      userAssignmentModel.getExcludedUsernames().removeIf(username -> username.equals(user.getName()));
    }
  }

  public void createOrUpdateRole() {
    var isReloadTree = false;
    var existedRole = RoleUtils.findRole(selectedRole.getName());
    if (isCreationMode && nonNull(existedRole)) {
      var message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          cms("/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/DuplicateRole", existedRole.getName()),
          EMPTY);
      FacesContext.getCurrentInstance().addMessage(null, message);
      FacesContext.getCurrentInstance().validationFailed();
      return;
    }

    if (nonNull(existedRole)) {
      existedRole.setDisplayNameTemplate(selectedRole.getDisplayName());
      existedRole.setDisplayDescriptionTemplate(selectedRole.getDescription());
      addRoleGrowlMessage(FacesMessage.SEVERITY_INFO, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/UpdateRoleSuccess", selectedRole.getName());
    } else {
      var parentRole = RoleUtils.findRole(selectedParentRole.getName());
      if (isNull(parentRole)) {
        parentRole = roleTopLevel;
      }
      try {
        var newRole = NewRole.create(selectedRole.getName())
              .parentRole(parentRole)
              .displayName(selectedRole.getDisplayName())
              .description(selectedRole.getDescription())
              .toNewRole();
        existedRole = ISecurityContext.current().roles().create(newRole);
        isReloadTree = true;
        addRoleGrowlMessage(FacesMessage.SEVERITY_INFO, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/CreateRoleSuccess", selectedRole.getName());
      } catch (Exception ex) {
        addRoleGrowlMessage(FacesMessage.SEVERITY_ERROR, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/CreateRoleFailed", selectedRole.getName());
      }
    }
    if (nonNull(existedRole)) {
      assignUsersToRole(existedRole);
      isReloadTree = true;
    }
    if (isReloadTree) {
      roleTreeModel.getRoles().add(new RoleHolder(existedRole));
      roleTreeModel.reloadTree();
    }
    setCreationMode(false);
  }

  public void deleteSelectedRole() {
    if (isNull(selectedRole)) {
      return;
    }
    var existedRole = ISecurityContext.current().roles().find(selectedRole.getName());
    if (existedRole == null) {
      addRoleGrowlMessage(FacesMessage.SEVERITY_WARN, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/NotFoundRole", selectedRole.getName());
      return;
    }

    ISecurityContext.current().roles().delete(selectedRole.getName());
    roleTreeModel.getRoles().clear();
    roleTreeModel.setFilterKeyword(EMPTY);
    roleTreeModel.reloadTree();
    addRoleGrowlMessage(FacesMessage.SEVERITY_INFO, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/RemoveRoleSuccess", selectedRole.getName());
  }

  private void assignUsersToRole(IRole existedRole) {
    userAssignmentModel.getAddNewUsers().forEach(user -> {
      addOrRemoveRoleForUser(existedRole, user.getName(), false);
    });
    userAssignmentModel.setAddNewUsers(null);
  }

  private void addOrRemoveRoleForUser(IRole existedRole, String userName, boolean isRemove) {
    if (isNull(existedRole)) {
      return;
    }
    IUser foundUser = ISecurityContext.current().users().find(userName);
    if (nonNull(foundUser)) {
      foundUser.getRoles()
          .stream()
          .filter(role -> role.getSecurityMemberId().equals(existedRole.getSecurityMemberId()))
          .findFirst()
          .ifPresentOrElse((user) -> {
            if (isRemove) {
              foundUser.removeRole(existedRole);
            }
          }, () -> {
            foundUser.addRole(existedRole);
          });
    }
  }

  public List<String> getDistinctExcludedUsernames() {
    return userAssignmentModel.getExcludedUsernames().stream().distinct().collect(Collectors.toList());
  }

  private void addRoleGrowlMessage(Severity severity, String cmsURL, Object... cmsParam) {
    var message = new FacesMessage(severity, cms(cmsURL, cmsParam), EMPTY);
    FacesContext.getCurrentInstance().addMessage("role-management-growl-message", message);
  }

  public String getRoleInformationHeaderDialog() {
    var roleName = isNull(getSelectedRole()) ? EMPTY : getSelectedRole().getName();
    var cmsURL = isCreationMode() ? "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/RoleCreation" : "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/RoleDetails";
    return cms(cmsURL, roleName);
  }

  private String cms(String cmsURL, Object... param) {
    return Ivy.cms().co(cmsURL, Arrays.asList(param));
  }

  public RoleTreeDataModel getRoleTreeModel() {
    return roleTreeModel;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public void setRoleTreeModel(RoleTreeDataModel roleTreeModel) {
    this.roleTreeModel = roleTreeModel;
  }

  public RoleHolder getSelectedRole() {
    return selectedRole;
  }

  public void setSelectedRole(RoleHolder selectedRole) {
    this.selectedRole = selectedRole;
  }

  public RoleDTO getSelectedParentRole() {
    return selectedParentRole;
  }

  public void setSelectedParentRole(RoleDTO selectedParentRole) {
    this.selectedParentRole = selectedParentRole;
  }

  public boolean isCreationMode() {
    return isCreationMode;
  }

  public void setCreationMode(boolean isCreationMode) {
    this.isCreationMode = isCreationMode;
  }

  public boolean isCanModifyRoleInformation() {
    return canModifyRoleInformation;
  }

  public void setCanModifyRoleInformation(boolean canModifyRoleInformation) {
    this.canModifyRoleInformation = canModifyRoleInformation;
  }

  public boolean isCanModifyUserAssignment() {
    return canModifyUserAssignment;
  }

  public void setCanModifyUserAssignment(boolean canModifyUserAssignment) {
    this.canModifyUserAssignment = canModifyUserAssignment;
  }

  public UserAssignedDataModel getUserAssignmentModel() {
    return userAssignmentModel;
  }

  public void setUserAssignmentModel(UserAssignedDataModel userAssignmentModel) {
    this.userAssignmentModel = userAssignmentModel;
  }

  public boolean isCanCreateRole() {
    return canCreateRole;
  }

  public void setCanCreateRole(boolean canCreateRole) {
    this.canCreateRole = canCreateRole;
  }

  public boolean isCanDeleteRole() {
    return canDeleteRole;
  }

  public void setCanDeleteRole(boolean canDeleteRole) {
    this.canDeleteRole = canDeleteRole;
  }

  public boolean isCanMoveRole() {
    return canMoveRole;
  }

  public void setCanMoveRole(boolean canMoveRole) {
    this.canMoveRole = canMoveRole;
  }
}
