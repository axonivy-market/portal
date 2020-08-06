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
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
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
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

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

  public static List<IApplication> findApps(List<String> appNames) throws PortalIvyDataException {
    Objects.requireNonNull(appNames, "The appNames must not be null");

    List<IApplication> apps = new ArrayList<>();
    for (String appName : appNames) {
      apps.add(findApp(appName));
    }
    return apps;
  }

  public static IUser findUser(final String username, IApplication app) throws PortalIvyDataException {
    Objects.requireNonNull(username, "The username must not be null");
    requireNonNull(app);

    IUser user = app.getSecurityContext().users().find(username);
    if (user == null) {
      throw new PortalIvyDataException(app.getName(), String.format("%s:%s", PortalIvyDataErrorType.USER_NOT_FOUND.toString(), username));
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

    return app.getSecurityContext()
      .users()
      .paged()
      .stream()
      .filter(user -> !StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, user.getName()))
      .collect(Collectors.toList());
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
        Ivy.log().error("Can't find user {0}", e, username);
        return null;
      }
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
   *  find all users, exclude SYSTEM user and current user in application and convert them to {@link UserDTO}
   * @param app {@link IApplication}
   *  @return all users in application, exclude SYSTEM user and current user
   */
  public static List<UserDTO> findAllUserDTOExceptCurrentUserByApplication(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      String sessionUsername = Ivy.session().getSessionUserName();
      return app.getSecurityContext()
          .users()
          .paged()
          .stream()
          .map(user -> new UserDTO(user))
          .filter(userDTO -> !StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, userDTO.getName())
              && !StringUtils.equals(sessionUsername, userDTO.getName()))
          .collect(Collectors.toList());
    });
  }
  
  public static UserDTO findUserDTO(final String username, IApplication app) {
    Objects.requireNonNull(username, "The username must not be null");
    requireNonNull(app);
    return IvyExecutor.executeAsSystem(() -> {
      IUser user = app.getSecurityContext().users().find(username);
      if (user == null) {
        throw new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.USER_NOT_FOUND.toString());
      }
      return new UserDTO(user);
    });
  }
  
  public static List<RoleDTO> findAllRoleDTO(IApplication app) {
    requireNonNull(app);
    return IvyExecutor.executeAsSystem(() -> {
      return CollectionUtils.emptyIfNull(app.getSecurityContext().getRoles())
          .stream()
          .filter(role -> role.getProperty("HIDE") == null)
          .map(role -> new RoleDTO(role))
          .collect(Collectors.toList());
    });
  }
  
  public static SecurityMemberDTO findSecurityMemberByName(String securityMemberName) {
    IApplication app = Ivy.wf().getApplication();
    SecurityMemberDTO member = null;
    if (securityMemberName.startsWith("#")) {
      member = findSecurityUserByName(securityMemberName.replace("#", ""), app);
    } else {
      member = findSecurityRoleByName(securityMemberName, app);
    }
    
    return member;
  }

  private static SecurityMemberDTO findSecurityUserByName(String securityMemberName, IApplication app) {
    UserDTO userDTO = null;
    try {
      userDTO = findUserDTO(securityMemberName, app);
    } catch (Exception ex) {
      Ivy.log().error("Error in getting security members within app {0}", ex, app.getName());
    }
    return SecurityMemberDTOMapper.mapFromUserDTO(userDTO);
  }

  private static SecurityMemberDTO findSecurityRoleByName(String securityMemberName, IApplication app) {
    List<RoleDTO> roles = null;
    try {
      roles = findAllRoleDTO(app).stream()
              .filter(role -> StringUtils.equalsIgnoreCase(role.getName(), securityMemberName))
              .collect(Collectors.toList());
    } catch (Exception ex) {
      Ivy.log().error("Error in getting security members within app {0}", ex, app.getName());
    }
    List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromRoleDTOs(roles);
    return CollectionUtils.isEmpty(members) ? null : members.get(0);
  }

}
