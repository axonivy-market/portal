package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.boon.datarepo.Repo;

import ch.ivy.addon.portalkit.persistence.dao.UserDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.persistence.domain.User;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class UserSynchronizationService {

  private UserSynchronizationService() {}

  /**
   * Helps prevent multi-application synchronizing users, only 1 application is allowed to synchronize users. <br>
   * 
   * @return If no server is configured, return true<br>
   *         If multi-application is configured and the current application's name is in the first order of the list of
   *         configured application sorted by name in ascending order, return true else return false.
   */
  public static boolean isCurrentApplicationAllowedToSynchUser() {
    ServerService serverService = new ServerService();
    ApplicationService applicationService = new ApplicationService();

    List<Server> registerServers = serverService.findActiveServersNotLocalhost();
    if (CollectionUtils.isEmpty(registerServers)) {
      return true;
    }

    ServerWorkingOnDetector detector = new ServerWorkingOnDetector();
    Server workingOnServer = detector.getServerWorkingOn();

    List<Application> applications = applicationService.findOnlineApplicationByServerId(workingOnServer.getId());
    if (applications.size() < 2) {
      return true;
    }

    Application firstApplication = applications.stream()
        .sorted((app1, app2) -> app1.getName().compareTo(app2.getName()))
        .findFirst()
        .orElse(new Application());
    return Ivy.request().getApplication().getName().equals(firstApplication.getName()); 
  }
  
  public static void addUserToCacheAndUserService() {
    // check user available
    UserService userService = new UserService();
    List<User> usersCheck = userService.findByUserName(Ivy.session().getSessionUserName());

//    demo AD2 
//    demo AD3
    
    List<Application> apps = new ApplicationService().findAllIvyApplications();
    List<User> users = new ArrayList<>();
    
    CollectionUtils.emptyIfNull(apps).forEach(app -> {
      String applicationName = app.getName();
      Ivy.log().error("START A CHECK");
      usersCheck.forEach(x -> Ivy.log().error("app name {0}, server id {1}", x.getApplicationName(), x.getServerId()));
      boolean x = CollectionUtils
          .emptyIfNull(usersCheck)
          .stream()
          .filter(userchk -> userchk.getApplicationName().equals(applicationName)
              && userchk.getServerId() != -1L )
              .findAny().isPresent();
      if (!x){
        User user = new User();
        user.setUserName(Ivy.session().getSessionUserName());
        user.setFullUserName(Ivy.session().getSessionUser().getDisplayName());
        user.setApplicationName(applicationName);
        users.add(user);
      }
    });
    
    if (CollectionUtils.isNotEmpty(users)){
      userService.saveAll(users);
      UserDao userDao = new UserDao();
      users.addAll(DataCache.getAllUsersFromCache());
      Repo<Long, User> repo = userDao.buildRepoIndexedByUserName(users);
      String applicationName = Ivy.wf().getApplication().getName();
      DataCache.cacheAllUsers(applicationName, users);
      DataCache.cacheUsersRepo(applicationName, repo);
    }
  }
}
