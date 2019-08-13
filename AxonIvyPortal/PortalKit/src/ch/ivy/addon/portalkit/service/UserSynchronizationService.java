package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.boon.datarepo.Repo;

import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.persistence.dao.UserDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.persistence.domain.User;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivy.addon.portalkit.util.ApplicationUserCacheUtils;
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
    if (isSingleAppConfigured(applications)) {
      return true;
    }

    Application firstApplication =
        applications.stream().sorted((app1, app2) -> app1.getName().compareTo(app2.getName()))
            .collect(Collectors.toList()).get(0);
    if (Ivy.request().getApplication().getName().equals(firstApplication.getName())) {
      return true;
    }
    return false;
  }

  private static boolean isSingleAppConfigured(List<Application> applications) {
    return applications.size() < 2;
  }
  
	public static void addUserToCacheAndUserService() {
		// check user available
		UserService userService = new UserService();
		List<User> usersCheck = userService.findByUserName(Ivy.session()
				.getSessionUserName());
		Boolean uex = false;
		for (User userchk : usersCheck) {
			if (userchk.getApplicationName().equals(
					Ivy.wf().getApplication().getName())) {
				uex = true;
				break;
			}
		}
		if (!uex) { // set user if not found
			Ivy.log().warn(
					"USER NOT FOUND in User Cache:"
							+ Ivy.session().getSessionUserName());
			// Step 1 Create Remote User

			RemoteUser remoteUser = new RemoteUser();
			remoteUser.setId(Ivy.session().getSessionUser().getId());
			remoteUser.setName(Ivy.session().getSessionUser().getDisplayName()); // fullname
			remoteUser.setUsername(Ivy.session().getSessionUserName()); // name
			remoteUser.setAppName(Ivy.wf().getApplication().getName());
			Long serverId = 0l;

			try {
				ServerService ses = new ServerService();
				if (ses.findAll().size() > 0) {
					serverId = ses.findAll().get(0).getId();
					if (serverId == null) {
						serverId = -1L;
					}
				}
			} catch (Exception e) {
				Ivy.log().error(
						"The serverId could not be determined. Using serverId 0 for saving user:"
								+ Ivy.session().getSessionUserName());
			}

			remoteUser.setServerId(serverId);

			// Step 2 Convert Remote User to User and save
			List<RemoteUser> remoteUsers = new ArrayList<RemoteUser>();
			remoteUsers.add(remoteUser);
			List<User> users = ApplicationUserCacheUtils
					.convertToEntity(remoteUsers);
			userService.saveAll(users);
			Ivy.log().warn(
					"USER WAS SAVED for User Cache:"
							+ Ivy.session().getSessionUserName());

			// Step 3 get cache and get users from it. Add them to the created
			// user

			UserDao userDao = new UserDao();
			users.addAll(DataCache.getAllUsersFromCache());
			Repo<Long, User> repo = userDao.buildRepoIndexedByUserName(users);
			String applicationName = Ivy.wf().getApplication().getName();
			DataCache.cacheAllUsers(applicationName, users);
			DataCache.cacheUsersRepo(applicationName, repo);
			Ivy.log().warn(
					"USER WAS SET into User Cache:"
							+ Ivy.session().getSessionUserName());
		}
	}
}
