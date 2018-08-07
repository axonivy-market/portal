package ch.ivy.ws.portaldata.service;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Class is to provide Ivy environment data
 */
public final class IvyService {

  private IvyService() {}

  public static IApplication getApplication() {
    return Ivy.wf().getApplication();
  }

}
