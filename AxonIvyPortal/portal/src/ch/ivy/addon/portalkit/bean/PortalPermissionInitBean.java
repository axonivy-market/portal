package ch.ivy.addon.portalkit.bean;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.PortalPermissionGroup;
import ch.ivy.addon.portalkit.security.PortalSecurity;
import ch.ivy.addon.portalkit.util.SecuritySystemUtils;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.security.AccessState;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionAccess;
import ch.ivyteam.ivy.security.IPermissionGroup;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionGroupRepository;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.server.restricted.EngineMode;

public class PortalPermissionInitBean extends AbstractProcessStartEventBean {

  private List<IPermissionAccess> everyBodyPermisisonAccesses;

  public PortalPermissionInitBean() {
    super("Init Portal Permissions", "Create Portal permissions if missing");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    getEventBeanRuntime().poll().disable();
    initEveryBodyPermissionAccessList();
    initPermissions();
  }

  private void initEveryBodyPermissionAccessList() {
    ISecurityMember everybody = ISecurityContext.current().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
    everyBodyPermisisonAccesses = ISecurityContext.current()
        .securityDescriptor()
        .getPermissionAccesses(everybody);
  }

  private void initPermissions() {
    recreateAndGrantPermissions();
    if (EngineMode.isAnyOf(EngineMode.DEMO, EngineMode.DESIGNER_EMBEDDED) && isIvySecuritySystem()) {
      PortalSecurity.INSTANCE.assignPermissionsToDefaultUsers();
    }
  }

  private void recreateAndGrantPermissions() {
    IPermissionGroup portalPermissionGroup = createPortalPermissionGroup();
    IPermissionGroup taskPermissionGroup =
        createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.TASK_PERMISSIONS_GROUP);
    IPermissionGroup casePermissionGroup =
        createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.CASE_PERMISSIONS_GROUP);
    IPermissionGroup generalPermissionGroup =
        createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP);
    IPermissionGroup absenceAndSubPermissionGroup =
        createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.ABSENCE_AND_SUBSTITUTE_GROUP);
    IPermissionGroup statisticsPermissionGroup =
        createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.STATISTIC_GROUP);
    initSystemPermission(taskPermissionGroup, getTaskPermissions());
    initSystemPermission(casePermissionGroup, getCasePermissions());
    initSystemPermission(generalPermissionGroup, getGeneralPermissions());
    initSystemPermission(absenceAndSubPermissionGroup, getAbsenceAndSubstitutePermissions());
    initSystemPermission(statisticsPermissionGroup, getPortalPermissionsByGroup(PortalPermissionGroup.STATISTIC_GROUP));
  }

  private void initSystemPermission(IPermissionGroup permissionGroup, List<IPermission> permissions) {
    for (IPermission permission : permissions) {
      if (!hasPermission(permissionGroup, permission)) {
        IPermissionGroupRepository.instance().addPermission(permissionGroup, permission);
      }
    }
  }

  private List<IPermission> getTaskPermissions() {
    List<IPermission> result = getPortalPermissionsByGroup(PortalPermissionGroup.TASK_PERMISSIONS_GROUP);
    result.addAll(Arrays.asList(IPermission.TASK_READ_ALL, IPermission.TASK_RESET_OWN_WORKING_TASK,
        IPermission.TASK_RESET, IPermission.TASK_WRITE_ACTIVATOR, IPermission.TASK_PARK_OWN_WORKING_TASK,
        IPermission.TASK_WRITE_EXPIRY_TIMESTAMP, IPermission.TASK_WRITE_ORIGINAL_PRIORITY,
        IPermission.TASK_WRITE_DESCRIPTION, IPermission.TASK_WRITE_NAME, IPermission.TASK_READ_OWN_CASE_TASKS,
        IPermission.TASK_DESTROY, IPermission.TASK_WRITE_DELAY_TIMESTAMP,
        IPermission.TASK_WRITE_EXPIRY_ACTIVATOR));
    return result;
  }

  /**
   * Create and grant permission for role Everybody if the permission didn't change by user
   * 
   * @param permission
   * @return the granted permission
   */
  private IPermission createAndGrantPermission(PortalPermission permission) {
    IPermission iPermission = IPermissionRepository.instance().findByName(permission.getValue());
    if (Objects.isNull(iPermission)) {
      // If Portal added a new permission, create that permission and grant to Everybody
      iPermission = IPermissionRepository.instance().create(permission.getValue());
      PortalSecurity.INSTANCE.grantPermissionToEverybody(iPermission.getName());
    } else {
      // Otherwise check access state of existing permission.
      // If the permission doesn't have any change, grant it to Everybody
      if (everyBodyPermisisonAccesses.stream()
          .filter(access -> access.getPermission().getId() == permission.getPermission().getId())
          .filter(access -> access.getAccessState() == AccessState.NOT_GRANTED_OR_DENIED)
          .findFirst().isPresent()) {
        PortalSecurity.INSTANCE.grantPermissionToEverybody(permission.getPermission().getName());
      }
    }
    return permission.getPermission();
  }

  private boolean isIvySecuritySystem() {
    return SecuritySystemUtils.isIvySecuritySystem();
  }

  private List<IPermission> getCasePermissions() {
    List<IPermission> result = getPortalPermissionsByGroup(PortalPermissionGroup.CASE_PERMISSIONS_GROUP);
    result.addAll(Arrays.asList(IPermission.CASE_DESTROY, IPermission.CASE_WRITE_DESCRIPTION, IPermission.CASE_WRITE_NAME,
        IPermission.CASE_READ_ALL));
    return result;
  }

  private List<IPermission> getGeneralPermissions() {
    List<IPermission> result = getPortalPermissionsByGroup(PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP);
    result.addAll(Arrays.asList(IPermission.DOCUMENT_WRITE, IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE,
        IPermission.DOCUMENT_READ, IPermission.ROLE_READ_ALL, IPermission.ROLE_CREATE, IPermission.ROLE_DELETE,
        IPermission.ROLE_MOVE));
    return result;
  }

  private List<IPermission> getAbsenceAndSubstitutePermissions() {
    return Arrays.asList(IPermission.USER_CREATE_ABSENCE, IPermission.USER_READ_ABSENCES,
        IPermission.USER_DELETE_ABSENCE, IPermission.USER_CREATE_OWN_ABSENCE, IPermission.USER_READ_OWN_ABSENCES,
        IPermission.USER_DELETE_OWN_ABSENCE, IPermission.USER_CREATE_OWN_SUBSTITUTE, IPermission.USER_CREATE_SUBSTITUTE,
        IPermission.USER_READ_SUBSTITUTES);
  }

  private List<IPermission> getPortalPermissionsByGroup(PortalPermissionGroup permissionGroup) {
    List<IPermission> result = new ArrayList<>();
    List<PortalPermission> portalPermissions = Stream.of(PortalPermission.values())
        .filter(p -> p.getGroup() == permissionGroup).collect(toList());
    for (PortalPermission permission : portalPermissions) {
      result.add(createAndGrantPermission(permission));
    }
    return result;
  }

  private boolean hasPermission(IPermissionGroup permissionGroup, IPermission permission) {
    return permissionGroup.getPermissions().stream().anyMatch(p -> p.getId() == permission.getId());
  }

  private IPermissionGroup createPortalPermissionGroup() {
    IPermissionGroup rootGroup = ISecurityContext.current().securityDescriptor().getSecurityDescriptorType().getRootPermissionGroup();
    return createPermissionsGroup(rootGroup, PortalPermissionGroup.PORTAL_PERMISSION_GROUP);
  }

  private IPermissionGroup createPermissionsGroup(IPermissionGroup parent, PortalPermissionGroup group) {
    IPermissionGroup permissionGroup = IPermissionGroupRepository.instance().findByName(group.getValue());
    if (Objects.isNull(permissionGroup)) {
      permissionGroup = IPermissionGroupRepository.instance().create(parent, group.getValue());
    }
    return permissionGroup;
  }
}
