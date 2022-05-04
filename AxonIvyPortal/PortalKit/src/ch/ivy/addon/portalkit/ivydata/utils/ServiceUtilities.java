package ch.ivy.addon.portalkit.ivydata.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;

public class ServiceUtilities {

  private ServiceUtilities() {}

  public static IUser findUser(final String username, IApplication app) {
    Objects.requireNonNull(username, "The username must not be null");
    requireNonNull(app);

    IUser user = app.getSecurityContext().users().find(username);
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

    List<IRole> roles = new ArrayList<>(app.getSecurityContext().roles().all());
    roles.removeIf(role -> role.getProperty(AdditionalProperty.HIDE.toString()) != null);
    roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));

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

  public static IUser findUser(String username) {
    return IvyExecutor.executeAsSystem(() -> {
      return findUser(username, IApplication.current());
    });
  }

  public static UserDTO findUserDTO(final String username, IApplication app) {
    Objects.requireNonNull(username, "The username must not be null");
    requireNonNull(app);
    return IvyExecutor.executeAsSystem(() -> {
      IUser user = app.getSecurityContext().users().find(username);
      return new UserDTO(user);
    });
  }

  public static List<RoleDTO> findAllRoleDTO(IApplication app) {
    requireNonNull(app);
    return IvyExecutor.executeAsSystem(() -> {
      return CollectionUtils.emptyIfNull(app.getSecurityContext().roles().all())
          .stream()
          .filter(role -> role.getProperty(AdditionalProperty.HIDE.toString()) == null)
          .map(role -> new RoleDTO(role))
          .collect(Collectors.toList());
    });
  }

  public static SecurityMemberDTO findSecurityMemberByName(String securityMemberName) {
    IApplication app = IApplication.current();
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
    userDTO = findUserDTO(securityMemberName, app);
    return SecurityMemberDTOMapper.mapFromUserDTO(userDTO);
  }

  private static SecurityMemberDTO findSecurityRoleByName(String securityMemberName, IApplication app) {
    List<RoleDTO> roles = null;
    roles = findAllRoleDTO(app).stream()
            .filter(role -> StringUtils.equalsIgnoreCase(role.getName(), securityMemberName))
            .collect(Collectors.toList());
    List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromRoleDTOs(roles);
    return CollectionUtils.isEmpty(members) ? null : members.get(0);
  }

}
