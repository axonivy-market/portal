package ch.ivy.addon.portalkit.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.dao.ServerDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivyteam.ivy.environment.Ivy;

public class UserSynchronizationService {

  /**
   * Helps prevent multi-application synchronizing users, only 1 application is allowed to synchronize users. <br>
   * 
   * @return If no server is configured, return true<br>
   *         If multi-application is configured and the current application is the first application that configured in
   *         Admin Settings, {@link Application#getMenuOrdinal()} {@code = 1} , return true<br>
   *         , else return false.
   */
  public static boolean isCurrentApplicationAllowedToSynchUser() {
    List<Server> registerServers = new ServerDao().findAll();
    if (CollectionUtils.isEmpty(registerServers)) {
      return true;
    }

    ServerWorkingOnDetector detector = new ServerWorkingOnDetector();
    Server workingOnServer = detector.getServerWorkingOn();
    ApplicationDao applicationDao = new ApplicationDao();
    int firstMenuOrdinal =
        applicationDao.findAllIvyApplications().stream().mapToInt(app -> app.getMenuOrdinal().intValue()).min()
            .orElse(0);
    List<Application> applications =
        applicationDao.findByNameAndServerId(Ivy.request().getApplication().getName(), workingOnServer.getId());
    for (Application application : applications) {
      if (application.getMenuOrdinal() == firstMenuOrdinal) {
        return true;
      }
    }
    return false;
  }
}
