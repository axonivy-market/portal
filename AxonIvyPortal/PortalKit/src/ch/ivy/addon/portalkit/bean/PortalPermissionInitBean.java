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
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionGroup;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionGroupRepository;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;

public class PortalPermissionInitBean extends AbstractProcessStartEventBean {

  public PortalPermissionInitBean() {
    super("Init Portal Permissions", "Create Portal permissions if missing");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, String configuration) {
    super.initialize(eventRuntime, configuration);
    getEventBeanRuntime().setPollTimeInterval(0);
    initPermissions();
  }

  private void initPermissions() {
    recreateAndGrantPermissions();
    PortalSecurity.INSTANCE.assignPermissionsToDefaultUsers();
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
    IPermissionGroup expressPermissionGroup =
        createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.EXPRESS_GROUP);
    initSystemPermission(taskPermissionGroup, getTaskPermissions());
    initSystemPermission(casePermissionGroup, getCasePermissions());
    initSystemPermission(generalPermissionGroup, getGeneralPermissions());
    initSystemPermission(absenceAndSubPermissionGroup, getAbsenceAndSubstitutePermissions());
    initSystemPermission(statisticsPermissionGroup, getPortalPermissionsByGroup(PortalPermissionGroup.STATISTIC_GROUP));
    initSystemPermission(expressPermissionGroup, getPortalPermissionsByGroup(PortalPermissionGroup.EXPRESS_GROUP));
    grantPortalPermissionsForEverybody(Arrays.asList(
            PortalPermission.STATISTIC_ADD_DASHBOARD_CHART, PortalPermission.EXPRESS_CREATE_WORKFLOW, PortalPermission.ACCESS_FULL_CASE_LIST,
            PortalPermission.ACCESS_FULL_TASK_LIST, PortalPermission.ACCESS_FULL_PROCESS_LIST, PortalPermission.ACCESS_FULL_STATISTICS_LIST,
            PortalPermission.TASK_CASE_ADD_NOTE, PortalPermission.TASK_CASE_SHOW_MORE_NOTE, PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS,
            PortalPermission.SHOW_ALL_TASKS_OF_CASE, PortalPermission.TASK_DISPLAY_RESET_ACTION, PortalPermission.TASK_DISPLAY_RESERVE_ACTION,
            PortalPermission.TASK_DISPLAY_DELEGATE_ACTION, PortalPermission.DASHBOARD_WRITE_OWN));
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
        IPermission.TASK_WRITE_EXPIRY_ACTIVATOR,
        createPermission(PortalPermission.TASK_RESET_READY_FOR_JOIN)));
    return result;
  }

  private IPermission createPermission(PortalPermission permission) {
    IPermission iPermission = IPermissionRepository.instance().findByName(permission.getValue());
    if (Objects.isNull(iPermission)) {
      iPermission = IPermissionRepository.instance().create(permission.getValue());
    }
    return iPermission;
  }

  private List<IPermission> getCasePermissions() {
    List<IPermission> result = getPortalPermissionsByGroup(PortalPermissionGroup.CASE_PERMISSIONS_GROUP);
    result.addAll(Arrays.asList(IPermission.CASE_DESTROY, IPermission.CASE_WRITE_DESCRIPTION, IPermission.CASE_WRITE_NAME,
        IPermission.CASE_READ_ALL));
    return result;
  }

  private List<IPermission> getGeneralPermissions() {
    List<IPermission> result = getPortalPermissionsByGroup(PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP);
    result.addAll(Arrays.asList(IPermission.DOCUMENT_WRITE, IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE, IPermission.DOCUMENT_READ));
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
    List<PortalPermission> portalPermissions =
        Stream.of(PortalPermission.values()).filter(p -> p.getGroup() == permissionGroup).collect(toList());
    for (PortalPermission permission : portalPermissions) {
      result.add(createPermission(permission));
    }
    return result;
  }

  private void grantPortalPermissionsForEverybody(List<PortalPermission> iPermissions) {
    IRole everybody = Ivy.security().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
    PortalSecurity.INSTANCE.grantPermissionsToForSecurityMember(iPermissions, everybody);
  }


  private boolean hasPermission(IPermissionGroup permissionGroup, IPermission permission) {
    return permissionGroup.getPermissions().stream().anyMatch(p -> p.getId() == permission.getId());
  }

  private IPermissionGroup createPortalPermissionGroup() {
    IPermissionGroup rootGroup = IApplication.current().getSecurityDescriptor().getSecurityDescriptorType().getRootPermissionGroup();
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
