package ch.ivy.addon.portalkit.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionAccess;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.server.restricted.EngineMode;

public enum PortalSecurity {
  INSTANCE;

  private static final class Username {
    private static final String ADMIN = "admin";
    private static final String DEMO = "demo";
    private static final String GUEST = "guest";
    private static final String DEVELOPER = "Developer";
    private Username() {}
  }
  
  private static class Permissions {
    @SuppressWarnings("deprecation")
    private static final List<IPermission> ADMIN_USER_ADDITIONAL = Arrays.asList(
            IPermission.ADMINISTRATE_WORKFLOW,
            IPermission.TASK_READ_ALL,
            IPermission.TASK_PARK_OWN_WORKING_TASK,
            IPermission.TASK_WRITE_ACTIVATOR,
            IPermission.TASK_WRITE_DESCRIPTION,
            IPermission.TASK_WRITE_NAME,
            IPermission.TASK_WRITE_ORIGINAL_PRIORITY,
            IPermission.TASK_WRITE_EXPIRY_TIMESTAMP,
            IPermission.TASK_RESET_OWN_WORKING_TASK,
            IPermission.CASE_DESTROY,
            IPermission.CASE_READ_ALL,
            IPermission.CASE_WRITE_DESCRIPTION,
            IPermission.CASE_WRITE_NAME,
            IPermission.USER_CREATE_ABSENCE,
            IPermission.USER_CREATE_SUBSTITUTE,
            IPermission.USER_DELETE_ABSENCE,
            IPermission.USER_READ_ABSENCES,
            IPermission.USER_READ_SUBSTITUTES,
            IPermission.DOCUMENT_WRITE);
  
    private static final List<IPermission> DEMO_USER_ADDITIONAL = Arrays.asList(IPermission.DOCUMENT_WRITE);
  
    private static final List<IPermission> GUEST_USER_DENIED = Arrays.asList(
            IPermission.USER_CREATE_OWN_ABSENCE,
            IPermission.USER_READ_OWN_ABSENCES,
            IPermission.USER_DELETE_OWN_ABSENCE,
            IPermission.USER_READ_OWN_SUBSTITUTES,
            IPermission.USER_CREATE_OWN_SUBSTITUTE,
            IPermission.USER_DELETE_OWN_SUBSTITUTE,
            IPermission.USER_READ_OWN_SUBSTITUTIONS);
  
    private static final List<PortalPermission> DEVELOPER_USER_ADDTIONAL = Arrays.asList(PortalPermission.values());
    private Permissions() {}
  }

  public void assignPermissionsToDefaultUsers() {
    IApplication portalApplication = Ivy.request().getApplication();
    ISecurityContext securityContext = portalApplication.getSecurityContext();
    boolean isIvySecurity = securityContext.getExternalSecuritySystemName()
        .equals(ISecurityConstants.IVY_ENGINE_SECURITY_SYSTEM_PROVIDER_NAME);
    if (EngineMode.is(EngineMode.DEMO) && isIvySecurity) {
      IUser adminUser = securityContext.findUser(Username.ADMIN);
      if (adminUser != null) {
        for (IPermission permission : Permissions.ADMIN_USER_ADDITIONAL) {
          portalApplication.getSecurityDescriptor().grantPermission(permission, adminUser);
        }
        grantPermissionsToForSecurityMember(Arrays.asList(
            PortalPermission.STATISTIC_ANALYZE_TASK, PortalPermission.SHOW_CASE_DETAILS), adminUser);
      }

      IUser demoUser = securityContext.findUser(Username.DEMO);
      if (demoUser != null) {
        for (IPermission permission : Permissions.DEMO_USER_ADDITIONAL) {
          portalApplication.getSecurityDescriptor().grantPermission(permission, demoUser);
        }
      }

      IUser guestUser = securityContext.findUser(Username.GUEST);
      if (guestUser != null) {
        for (IPermission permission : Permissions.GUEST_USER_DENIED) {
          portalApplication.getSecurityDescriptor().denyPermission(permission, guestUser);
        }
      }

      IUser developerUser = securityContext.findUser(Username.DEVELOPER);
      if (developerUser != null) {
        grantPermissionsToForSecurityMember(Permissions.DEVELOPER_USER_ADDTIONAL, developerUser);
      }
    }
  }


  public void grantPermissionsToForSecurityMember(List<PortalPermission> iPermissions, ISecurityMember securityMember) {
    if (CollectionUtils.isEmpty(iPermissions) || securityMember == null) {
      return;
    }
    ISecurityDescriptor portalSecurity = Ivy.wf().getApplication().getSecurityDescriptor();
    List<IPermission> denniedPermission = portalSecurity.getPermissionAccesses(securityMember).stream()
        .filter(IPermissionAccess::isDenied).map(IPermissionAccess::getPermission).collect(Collectors.toList());
    iPermissions.forEach(iPermission -> {
      IPermission ivyPermission = IPermissionRepository.get().findByName(iPermission.getValue());
      if (!denniedPermission.contains(ivyPermission)) {
        portalSecurity.grantPermission(ivyPermission, securityMember);
      }
    });
  }
}