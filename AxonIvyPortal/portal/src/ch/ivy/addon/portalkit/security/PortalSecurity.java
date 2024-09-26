package ch.ivy.addon.portalkit.security;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.user.IUserRepository;

public enum PortalSecurity {
  INSTANCE;

  private static ISecurityMember everybody = null;

  private static final class Username {
    private static final String ADMIN = "admin";
    private static final String DEMO = "demo";
    private static final String GUEST = "guest";
    private Username() {}
  }

  private static class Permissions {
    private static final List<IPermission> ADMIN_USER_ADDITIONAL = Arrays.asList(
            IPermission.TASK_READ_ALL,
            IPermission.TASK_PARK_OWN_WORKING_TASK,
            IPermission.TASK_WRITE_ACTIVATOR,
            IPermission.TASK_WRITE_DESCRIPTION,
            IPermission.TASK_WRITE_NAME,
            IPermission.TASK_WRITE_ORIGINAL_PRIORITY,
            IPermission.TASK_WRITE_EXPIRY_ACTIVATOR,
            IPermission.TASK_WRITE_EXPIRY_TIMESTAMP,
            IPermission.TASK_WRITE_DELAY_TIMESTAMP,
            IPermission.TASK_RESET_OWN_WORKING_TASK,
            IPermission.TASK_RESET,
            IPermission.TASK_DESTROY,
            IPermission.CASE_DESTROY,
            IPermission.CASE_READ_ALL,
            IPermission.CASE_WRITE_DESCRIPTION,
            IPermission.CASE_WRITE_NAME,
            IPermission.USER_CREATE_ABSENCE,
            IPermission.USER_CREATE_SUBSTITUTE,
            IPermission.USER_DELETE_ABSENCE,
            IPermission.USER_READ_ABSENCES,
            IPermission.USER_READ_SUBSTITUTES,
            IPermission.DOCUMENT_WRITE,
            IPermission.WORKFLOW_EVENT_READ_ALL,
            IPermission.ROLE_READ_ALL,
            IPermission.ROLE_CREATE,
            IPermission.ROLE_DELETE,
            IPermission.ROLE_MOVE);

    private static final List<IPermission> DEMO_USER_ADDITIONAL = Arrays.asList(IPermission.DOCUMENT_WRITE);

    private static final List<IPermission> GUEST_USER_DENIED = Arrays.asList(
            IPermission.USER_CREATE_OWN_ABSENCE,
            IPermission.USER_READ_OWN_ABSENCES,
            IPermission.USER_DELETE_OWN_ABSENCE,
            IPermission.USER_READ_OWN_SUBSTITUTES,
            IPermission.USER_CREATE_OWN_SUBSTITUTE,
            IPermission.USER_DELETE_OWN_SUBSTITUTE,
            IPermission.USER_READ_OWN_SUBSTITUTIONS);

    private static final List<PortalPermission> EVERYBODY_PERMISSIONS = Arrays.asList(
        PortalPermission.ACCESS_FULL_CASE_LIST, PortalPermission.ACCESS_FULL_TASK_LIST,
        PortalPermission.ACCESS_FULL_PROCESS_LIST,
        PortalPermission.TASK_CASE_ADD_NOTE, PortalPermission.TASK_CASE_SHOW_MORE_NOTE,
        PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS, PortalPermission.SHOW_ALL_TASKS_OF_CASE,
        PortalPermission.TASK_DISPLAY_RESET_ACTION, PortalPermission.TASK_DISPLAY_RESERVE_ACTION,
        PortalPermission.TASK_DISPLAY_DELEGATE_ACTION, PortalPermission.DASHBOARD_WRITE_OWN,
        PortalPermission.SHOW_CASE_DETAILS, PortalPermission.DASHBOARD_SHARE_LINK, 
        PortalPermission.TASK_DETAILS_SHARE_LINK, PortalPermission.CASE_DETAILS_SHARE_LINK);

    private static final List<PortalPermission> ALL_PORTAL_PERMISSIONS = Arrays.asList(PortalPermission.values());
    private Permissions() {}
  }

  public void assignPermissionsToDefaultUsers() {
    IUserRepository userRepo = ISecurityContext.current().users();
    IUser developerUser = userRepo.findWithExternalLookup(ISecurityConstants.DEVELOPER_USER_NAME);
    if (developerUser != null) {
      grantPermissionsToSecurityMember(Permissions.ALL_PORTAL_PERMISSIONS, developerUser);
    }

    IUser adminUser = userRepo.findWithExternalLookup(Username.ADMIN);
    if (adminUser != null) {
      for (IPermission permission : Permissions.ADMIN_USER_ADDITIONAL) {
        grantPermission(permission.getName(), adminUser);
      }
      grantPermissionsToSecurityMember(Permissions.ALL_PORTAL_PERMISSIONS, adminUser);
    }

    IUser demoUser = userRepo.findWithExternalLookup(Username.DEMO);
    if (demoUser != null) {
      for (IPermission permission : Permissions.DEMO_USER_ADDITIONAL) {
        grantPermission(permission.getName(), demoUser);
      }
    }

    IUser guestUser = userRepo.findWithExternalLookup(Username.GUEST);
    if (guestUser != null) {
      for (IPermission permission : Permissions.GUEST_USER_DENIED) {
        denyPermission(permission.getName(), guestUser);
      }
    }
  }

  public void grantPermissionsToSecurityMember(List<PortalPermission> iPermissions, ISecurityMember securityMember) {
    if (CollectionUtils.isEmpty(iPermissions) || securityMember == null) {
      return;
    }
    iPermissions.forEach(iPermission -> {
      grantPermission(iPermission.getValue(), securityMember);
    });
  }

  public void grantPermissionToEverybody(String permissionName) {
    var permission = PortalPermission.findPermission(permissionName);
    if (Objects.isNull(permission) || !Permissions.EVERYBODY_PERMISSIONS.contains(permission)) {
      return;
    }
    grantPermission(permissionName, getEverybodyRole());
  }

  private ISecurityMember getEverybodyRole() {
    if (everybody == null) {
      everybody = ISecurityContext.current().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
    }
    return everybody;
  }

  private void grantPermission(String permissionName, ISecurityMember securityMember) {
    var iPermission = IPermissionRepository.instance().findByName(permissionName);
    if (iPermission != null) {
      ISecurityContext.current().securityDescriptor().grantPermission(iPermission, securityMember);
    }
  }

  private void denyPermission(String permissionName, ISecurityMember securityMember) {
    var iPermission = IPermissionRepository.instance().findByName(permissionName);
    if (iPermission != null) {
      ISecurityContext.current().securityDescriptor().denyPermission(iPermission, securityMember);
    }
  }
}