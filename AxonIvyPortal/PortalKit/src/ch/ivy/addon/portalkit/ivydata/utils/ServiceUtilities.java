package ch.ivy.addon.portalkit.ivydata.utils;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;

public class ServiceUtilities {

  public static IApplication findApp(final String appName) throws PortalIvyDataException {
    Objects.requireNonNull(appName, "The appName must not be null");
    
    IApplication app = getServer().getApplicationConfigurationManager().findApplication(appName);
    if (app == null || app.getActivityState() != ActivityState.ACTIVE) {
      throw new PortalIvyDataException(appName, PortalIvyDataErrorType.APP_NOT_FOUND.toString());
    }
    return app;
  }

  public static IUser findUser(final String username, IApplication app) throws PortalIvyDataException {
    Objects.requireNonNull(username, "The username must not be null");
    Objects.requireNonNull(app, "The application must not be null");
    
    IUser user = app.getSecurityContext().findUser(username);
    if (user == null) {
      throw new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.USER_NOT_FOUND.toString());
    }
    return user;
  }

  /**
   * Finds all of the users within the given app, except the system user
   * @param app
   * @return users
   */
  @SuppressWarnings("unchecked")
  public static List<IUser> findAllUsers(IApplication app) {
    Objects.requireNonNull(app, "The application must not be null");
    
    Optional<Object> cacheValueOpt = IvyCacheService.newInstance().getSessionCacheValue(app.getName(), IvyCacheIdentifier.USERS_IN_APPLICATION);
    if (cacheValueOpt.isPresent()) {
      return (List<IUser>) cacheValueOpt.get();
    }
    
    List<IUser> users = new ArrayList<>(app.getSecurityContext().getUsers());
    users.removeIf(user -> StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, user.getName()));
    
    IvyCacheService.newInstance().setSessionCache(app.getName(), IvyCacheIdentifier.USERS_IN_APPLICATION, users);
    return users;
  }

  public static List<IProcessModelVersion> getActiveReleasedPmvs(IApplication app) {
    Objects.requireNonNull(app, "The application must not be null");
    
    return app.getProcessModels().stream().filter(pm -> pm.getActivityState() == ActivityState.ACTIVE)
        .map(IProcessModel::getReleasedProcessModelVersion)
        .filter(pmv -> pmv != null && pmv.getActivityState() == ActivityState.ACTIVE).collect(Collectors.toList());
  }
  
  public static IvyApplication toIvyApplication(String appName, String appDisplayName) {
    IvyApplication ivyApplication = new IvyApplication();
    ivyApplication.setName(appName);
    ivyApplication.setDisplayName(appDisplayName);
    ivyApplication.setActive(true);
    return ivyApplication;
  }
}
