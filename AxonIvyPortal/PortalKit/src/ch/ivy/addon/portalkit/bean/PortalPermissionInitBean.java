package ch.ivy.addon.portalkit.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.PortalPermissionGroup;
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
    IPermissionGroup portalPermissionGroup = createPortalPermissionGroup();
    List<PortalPermission> portalPermissionsList = Arrays.asList(PortalPermission.values());
    for (PortalPermission permission : portalPermissionsList) {
      createPermission(portalPermissionGroup, permission);
    }
  }

  private void createPermission(IPermissionGroup portalPermissionGroup, PortalPermission permission) {
    IPermission iPermission = IPermissionRepository.get().findByName(permission.getValue());
    if (Objects.isNull(iPermission)) {
      iPermission = IPermissionRepository.get().create(permission.getValue());
      IPermissionGroupRepository.get().addPermission(portalPermissionGroup, iPermission);
    }
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

}
