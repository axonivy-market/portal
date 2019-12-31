package ch.ivy.addon.portalkit.ivydata.utils;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class ServiceUtilities {

  private ServiceUtilities() {}

  public static IApplication findApp(final String appName) throws PortalIvyDataException {
    Objects.requireNonNull(appName, "The appName must not be null");

    IApplication app = getServer().getApplicationConfigurationManager().findApplication(appName);
    if (app == null || app.getActivityState() != ActivityState.ACTIVE) {
      throw new PortalIvyDataException(appName, PortalIvyDataErrorType.APP_NOT_FOUND.toString());
    }
    return app;
  }

  public static List<IApplication> findApps(List<String> appNames) {
    Objects.requireNonNull(appNames, "The appNames must not be null");

    List<IApplication> apps = new ArrayList<>();
    for (String appName : appNames) {
      IApplication app = getServer().getApplicationConfigurationManager().findApplication(appName);
      if (app != null && app.getActivityState() == ActivityState.ACTIVE) {
        apps.add(app);
      }
    }
    return apps;
  }

  public static IUser findUser(final String username, IApplication app) throws PortalIvyDataException {
    Objects.requireNonNull(username, "The username must not be null");
    requireNonNull(app);

    IUser user = app.getSecurityContext().findUser(username);
    if (user == null) {
      throw new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.USER_NOT_FOUND.toString());
    }
    return user;
  }

  private static void requireNonNull(IApplication app) {
    Objects.requireNonNull(app, "The application must not be null");
  }

  /**
   * Finds all of the users within the given app, except the system user
   * 
   * @param app
   * @return users
   */
  public static List<IUser> findAllUsers(IApplication app) {
    requireNonNull(app);

    List<IUser> users = new ArrayList<>(app.getSecurityContext().getUsers());
    users.removeIf(user -> StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, user.getName()));

    return users;
  }

  /**
   * Finds all of the users within the given app, except the roles have the HIDE property
   * 
   * @param app
   * @return roles
   */
  @SuppressWarnings("unchecked")
  public static List<IRole> findAllRoles(IApplication app) {
    requireNonNull(app);

    Optional<Object> cacheValueOpt =
        IvyCacheService.newInstance().getSessionCacheValue(app.getName(), IvyCacheIdentifier.ROLES_IN_APPLICATION);
    if (cacheValueOpt.isPresent()) {
      return (List<IRole>) cacheValueOpt.get();
    }

    List<IRole> roles = new ArrayList<>(app.getSecurityContext().getRoles());
    roles.removeIf(role -> role.getProperty("HIDE") != null);

    IvyCacheService.newInstance().setSessionCache(app.getName(), IvyCacheIdentifier.ROLES_IN_APPLICATION, roles);
    return roles;
  }

  public static List<IProcessModelVersion> getActiveReleasedPmvs(IApplication app) {
    requireNonNull(app);

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

  public static IUser findUser(String username, String appName) {
    return IvyExecutor.executeAsSystem(() -> {
      try {
        IApplication app = findApp(appName);
        return findUser(username, app);
      } catch (PortalIvyDataException e) {
        Ivy.log().error("Can't find user " + username, e);
        return null;
      }
    });
  }
  

  public static IWorkflowSession findUserWorkflowSession(String username, IApplication app) {
    if (Objects.equals(Ivy.wf().getApplication(), app)) {
      return Ivy.session();
    }

    ISecurityContext securityContext = app.getSecurityContext();
    return IvyExecutor.executeAsSystem(() -> {
      ISession session = securityContext.createSession();
      IUser user = securityContext.findUser(username);

      if (user != null) {
        String authenticationMode = "customAuth";
        session.authenticateSessionUser(user, authenticationMode, -1L);
      }
      return Ivy.wf().getWorkflowSession(session);
    });
  }

  public List<IApplication> getApplicationsRelatedToPortal() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<String> configuredApps =
        service.findAllIvyApplications().stream().map(Application::getName).collect(Collectors.toList());
    List<IApplication> apps;
    if (CollectionUtils.isEmpty(configuredApps)) {
      apps = IvyExecutor.executeAsSystem(
          () -> ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false));
    } else {
      apps = IvyExecutor.executeAsSystem(() -> ServiceUtilities.findApps(configuredApps));
      IApplication defaultApplication = ServerFactory.getServer().getApplicationConfigurationManager()
          .findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
      if (defaultApplication != null && defaultApplication.getActivityState() == ActivityState.ACTIVE) {
        apps.add(defaultApplication);
      }
    }
    return apps;
  }

  /**
   * find all users, exclude SYSTEM user in application and convert them to {@link UserDTO}
   * @param app {@link IApplication}
   * 
   * @return all users in application, exclude SYSTEM user
   */
  public static List<UserDTO> findAllUserDTOByApplication(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      return app.getSecurityContext().getUsers().stream().map(user -> new UserDTO(user))
          .filter(userDTO -> !StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, userDTO.getName()))
          .collect(Collectors.toList());
    });
  }
  
  /**
   *  find all users, exclude SYSTEM user and current user in application and convert them to {@link UserDTO}
   * @param app {@link IApplication}
   *  @return all users in application, exclude SYSTEM user and current user
   */
  public static List<UserDTO> findAllUserDTOExceptCurrentUserByApplication(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      String sessionUsername = Ivy.session().getSessionUserName();
      return app.getSecurityContext().getUsers().stream().map(user -> new UserDTO(user))
          .filter(userDTO -> !StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, userDTO.getName())
              && !StringUtils.equals(sessionUsername, userDTO.getName()))
          .collect(Collectors.toList());
    });
  }
  
  public static UserDTO findUserDTO(final String username, IApplication app) {
    Objects.requireNonNull(username, "The username must not be null");
    requireNonNull(app);
    return IvyExecutor.executeAsSystem(() -> {
      IUser user = app.getSecurityContext().findUser(username);
      if (user == null) {
        throw new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.USER_NOT_FOUND.toString());
      }
      return new UserDTO(user);
    });
  }
  
  public static List<RoleDTO> findAllRoleDTO(IApplication app) {
    requireNonNull(app);
    return IvyExecutor.executeAsSystem(() -> {
      List<IRole> roles = new ArrayList<>(app.getSecurityContext().getRoles());
      roles.removeIf(role -> role.getProperty("HIDE") != null);
      return roles.stream().map(role -> new RoleDTO(role)).collect(Collectors.toList());
    });
  }

}
