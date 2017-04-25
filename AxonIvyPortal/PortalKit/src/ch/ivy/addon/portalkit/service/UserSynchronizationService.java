package ch.ivy.addon.portalkit.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.dao.ServerDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivyteam.ivy.environment.Ivy;

public class UserSynchronizationService {
  private static final int FIRST_CONFIGURED_APPLICATION = 1;

  /**
   * Helps prevent multi-application synchronizing users, only 1 application is allowed to synchronize users. <br>
   * 
   * @return If no server is configured, return true<br>
   *         If multi-application is configured and the current application is the first application that configured in
   *         Admin Settings, {@link Application#getMenuOrdinal()} {@code = 1} , return true<br>
   *         , else return false.
   */
  public boolean isCurrentApplicationAllowedToSynchUser() {
    List<Server> registerServers = new ServerDao().findAll();
    if (CollectionUtils.isEmpty(registerServers)) {
      return true;
    }
    ServerWorkingOnDetector detector = new ServerWorkingOnDetector();
    Server workingOnServer = detector.getServerWorkingOn();
    ApplicationDao applicationDao = new ApplicationDao();
    List<Application> applications =
        applicationDao.findByNameAndServerId(Ivy.request().getApplication().getName(), workingOnServer.getId());
    for (Application application : applications) {
      if (application.getMenuOrdinal() == FIRST_CONFIGURED_APPLICATION) {
        return true;
      }
    }
    return false;
  }
}
