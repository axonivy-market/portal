package ch.ivy.addon.portalkit.bean;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.PortalPermissionGroup;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IPermissionGroup;
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
    IvyExecutor.executeOnceInAllProcessModelVersion(
        Ivy.request().getProcessModelVersion(),
        () -> {
          cleanAllPortalPermissionGroups();
          IPermissionGroup portalPermissionGroup = createPortalPermissionGroup();
          IPermissionGroup taskPermissionGroup = createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.TASK_PERMISSIONS_GROUP);
          IPermissionGroup casePermissionGroup = createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.CASE_PERMISSIONS_GROUP);
          IPermissionGroup generalPermissionGroup = createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.GENERAL_PERMISSIONS_GROUP);
          IPermissionGroup absenceAndSubPermissionGroup = createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.ABSENCE_AND_SUBSTITUTE_GROUP);
          IPermissionGroup statisticsPermissionGroup = createPermissionsGroup(portalPermissionGroup, PortalPermissionGroup.STATISTICS_GROUP);
          initSystemPermission(taskPermissionGroup, getTaskPermissions());
          initSystemPermission(casePermissionGroup, getCasePermissions());
          initSystemPermission(generalPermissionGroup, getGeneralPermissions());
          initSystemPermission(absenceAndSubPermissionGroup, getAbsenceAndSubstitutePermissions());
          initSystemPermission(statisticsPermissionGroup, getPortalStatisticsPermissions());
        });
  }

  private void initSystemPermission(IPermissionGroup permissionGroup, List<IPermission> permissions) {
    for (IPermission permission : permissions) {
      if (!hasPermission(permissionGroup, permission)) {
        IPermissionGroupRepository.get().addPermission(permissionGroup, permission);
      }
    }
  }

  private List<IPermission> getTaskPermissions() {
    List<IPermission> result = getPortalTaskPermission();
    result.addAll(Arrays.asList(IPermission.TASK_READ_ALL, IPermission.TASK_RESET_OWN_WORKING_TASK,
        IPermission.TASK_RESET, IPermission.TASK_WRITE_ACTIVATOR, IPermission.TASK_PARK_OWN_WORKING_TASK,
        IPermission.TASK_WRITE_EXPIRY_TIMESTAMP, IPermission.TASK_WRITE_ORIGINAL_PRIORITY,
        IPermission.TASK_WRITE_DESCRIPTION, IPermission.TASK_WRITE_NAME, IPermission.TASK_READ_OWN_CASE_TASKS));
    return result;
  }

  private List<IPermission> getPortalTaskPermission() {
    List<IPermission> result = new ArrayList<>();
    List<PortalPermission> portalTaskPermissions =
        Stream.of(PortalPermission.values()).filter(p -> p.getGroup() == PortalPermissionGroup.TASK_PERMISSIONS_GROUP)
            .collect(toList());
    for (PortalPermission permission : portalTaskPermissions) {
      result.add(createPermission(permission));
    }
    return result;
  }

  private IPermission createPermission(PortalPermission permission) {
    IPermission iPermission = IPermissionRepository.get().findByName(permission.getValue());
    if (Objects.isNull(iPermission)) {
      iPermission = IPermissionRepository.get().create(permission.getValue());
    }
    return iPermission;
  }

  private List<IPermission> getCasePermissions() {
    return Arrays.asList(IPermission.CASE_DESTROY, IPermission.CASE_WRITE_DESCRIPTION, IPermission.CASE_WRITE_NAME,
        IPermission.CASE_READ_ALL);
  }

  private List<IPermission> getGeneralPermissions() {
    return Arrays.asList(IPermission.DOCUMENT_WRITE, IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }

  private List<IPermission> getAbsenceAndSubstitutePermissions() {
    return Arrays.asList(IPermission.USER_CREATE_ABSENCE, IPermission.USER_READ_ABSENCES,
        IPermission.USER_DELETE_ABSENCE, IPermission.USER_CREATE_OWN_ABSENCE, IPermission.USER_READ_OWN_ABSENCES,
        IPermission.USER_DELETE_OWN_ABSENCE, IPermission.USER_CREATE_OWN_SUBSTITUTE,
        IPermission.USER_CREATE_SUBSTITUTE, IPermission.USER_READ_SUBSTITUTES);
  }
  
  private List<IPermission> getPortalStatisticsPermissions() {
    List<IPermission> result = new ArrayList<>();
    List<PortalPermission> portalStatisticsPermissions =
        Stream.of(PortalPermission.values()).filter(p -> p.getGroup() == PortalPermissionGroup.STATISTICS_GROUP).collect(toList());
    for (PortalPermission permission : portalStatisticsPermissions) {
      result.add(createPermission(permission));
    }
    return result;
  }

  private boolean hasPermission(IPermissionGroup permissionGroup, IPermission permission) {
    return permissionGroup.getPermissions().stream().anyMatch(p -> p.getId() == permission.getId());
  }

  private IPermissionGroup createPortalPermissionGroup() {
    IPermissionGroup rootGroup =
        Ivy.wf().getApplication().getSecurityDescriptor().getSecurityDescriptorType().getRootPermissionGroup();
    IPermissionGroup portalPermissionGroup =
        IPermissionGroupRepository.get().findByName(PortalPermissionGroup.PORTAL_PERMISSION_GROUP.getValue());
    if (Objects.isNull(portalPermissionGroup)) {
      portalPermissionGroup =
          IPermissionGroupRepository.get().create(rootGroup, PortalPermissionGroup.PORTAL_PERMISSION_GROUP.getValue());
    }
    return portalPermissionGroup;
  }

  private IPermissionGroup createPermissionsGroup(IPermissionGroup parent, PortalPermissionGroup group) {
    IPermissionGroup permissionGroup = IPermissionGroupRepository.get().findByName(group.getValue());
    if (Objects.isNull(permissionGroup)) {
      permissionGroup = IPermissionGroupRepository.get().create(parent, group.getValue());
    }
    return permissionGroup;
  }

  private void cleanAllPortalPermissionGroups() {
    IPermissionGroup permissionGroup =
        IPermissionGroupRepository.get().findByName(PortalPermissionGroup.PORTAL_PERMISSION_GROUP.getValue());
    if (Objects.nonNull(permissionGroup)) {
      IPermissionGroupRepository.get().delete(permissionGroup);
    }
  }
}
