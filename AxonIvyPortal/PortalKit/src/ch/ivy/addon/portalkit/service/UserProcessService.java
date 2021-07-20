package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.enums.JsonVariable;

public class UserProcessService extends JsonConfigurationService<UserProcess> {

  private static final String FAVORITE_PROCESSES = JsonVariable.FAVORITE_PROCESS.key;

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
    return FAVORITE_PROCESSES;
  }

}
