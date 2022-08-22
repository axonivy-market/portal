package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.enums.PortalVariable;

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
}
