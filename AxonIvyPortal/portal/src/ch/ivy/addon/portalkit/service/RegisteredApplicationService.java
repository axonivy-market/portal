package ch.ivy.addon.portalkit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class RegisteredApplicationService extends JsonConfigurationService<Application> {

  private static final String THIRD_PARTY_APPLICATIONS = PortalVariable.THIRD_PARTY_APP.key;
  private static RegisteredApplicationService instance;

  private RegisteredApplicationService() {}

  public static RegisteredApplicationService getInstance() {
    if (instance == null) {
      instance = new RegisteredApplicationService();
    }
    return instance;
  }
  @Override
  public Class<Application> getType() {
    return Application.class;
  }

  @Override
  public String getConfigKey() {
    return THIRD_PARTY_APPLICATIONS;
  }

  @Override
  public List<Application> getPublicConfig() {
    List<Application> applications = super.getPublicConfig();
    applications.removeIf(application -> {
      List<String> permissions = application.getPermissions();
      if (permissions == null) {
        return false;
      }
      return permissions.stream().noneMatch(RegisteredApplicationService::isSessionUserHasPermisson);
    });
    return applications;
  }

  @Override
  public List<Application> saveAllPublicConfig(List<Application> application) {
    return super.saveAllPublicConfig(application);
  }

  @Override
  public List<Application> findAll() {
    return super.findAll();
  }

  private static boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#")
        ? StringUtils.equals(Ivy.session().getSessionUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }
}
