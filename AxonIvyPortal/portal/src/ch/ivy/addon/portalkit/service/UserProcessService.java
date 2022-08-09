package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.enums.PortalVariable;

public class UserProcessService extends JsonConfigurationService<UserProcess> {

  private static UserProcessService instance;

  private UserProcessService() {}
  public static UserProcessService getInstance() {
    if (instance == null) {
      instance = new UserProcessService();
    }
    return instance;
  }

  @Override
  public Class<UserProcess> getType() {
    return UserProcess.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.FAVORITE_PROCESS.key;
  }

}
