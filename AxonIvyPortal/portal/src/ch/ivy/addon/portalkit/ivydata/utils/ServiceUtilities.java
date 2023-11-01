package ch.ivy.addon.portalkit.ivydata.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

public class ServiceUtilities {

  private ServiceUtilities() {}

  /**
   * @deprecated use {@link ServiceUtilities#findUser(String)} instead
   * @param username
   * @param app
   * @return list of user
   */
  @Deprecated(forRemoval = true, since = "9.4")
  public static IUser findUser(final String username, @SuppressWarnings("unused") IApplication app) {
    return findUser(username);
  }
  
  private static void requireNonNull(IApplication app) {
    Objects.requireNonNull(app, "The application must not be null");
  }

  /**
   * Finds all of the users within the given app, except the system user
   *
   * @deprecated use {@link ServiceUtilities#findAllUsers()} instead 
   * @param app
   * @return users
   */
  @Deprecated(forRemoval=true, since = "9.4")
  public static List<IUser> findAllUsers(@SuppressWarnings("unused") IApplication app) {
    return findAllUsers();
  }
  
  /**
   * Finds all of the users within the current application, except the system user
   *
   * @return users
   */
  public static List<IUser> findAllUsers() {
    return ISecurityContext.current()
      .users()
      .paged()
      .stream()
      .filter(user -> !StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, user.getName()))
      .collect(Collectors.toList());
  }

  /**
   * Finds all of the roles within the given app, except the roles have the HIDE property
   * @deprecated use {@link ServiceUtilities#findAllRoles()} instead
   * @param app
   * @return roles
   */
  @Deprecated(forRemoval=true, since = "9.4")
  public static List<IRole> findAllRoles(@SuppressWarnings("unused") IApplication app) {
    return findAllRoles();
  }
  
  /**
   * Finds all of the roles in security context, except the roles have the HIDE property
   * @return roles
   */
  @SuppressWarnings("unchecked")
  public static List<IRole> findAllRoles() {
    Optional<Object> cacheValueOpt =
        IvyCacheService.newInstance().getSessionCacheValue(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT);
    if (cacheValueOpt.isPresent()) {
      return (List<IRole>) cacheValueOpt.get();
    }

    List<IRole> roles = new ArrayList<>(ISecurityContext.current().roles().all());
    roles.removeIf(role -> role.getProperty(AdditionalProperty.HIDE.toString()) != null);
    roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));

    IvyCacheService.newInstance().setSessionCache(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT, roles);
    return roles;
  }
  
  public static List<IProcessModelVersion> getActiveReleasedPmvs(IApplication app) {
    requireNonNull(app);

    return app.getProcessModels()
          .stream()
          .filter(pm -> pm.getActivityState() == ActivityState.ACTIVE)
          .map(IProcessModel::getReleasedProcessModelVersion)
          .filter(pmv -> pmv != null && pmv.getActivityState() == ActivityState.ACTIVE)
          .collect(Collectors.toList());
  }

  public static IvyApplication toIvyApplication(String appName, String appDisplayName) {
    IvyApplication ivyApplication = new IvyApplication();
    ivyApplication.setName(appName);
    ivyApplication.setDisplayName(appDisplayName);
    ivyApplication.setActive(true);
    return ivyApplication;
  }

  public static IUser findUser(String username) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }

  public static UserDTO findUserDTO(final String username) {
    Objects.requireNonNull(username, "The username must not be null");
    return Sudo.get(() -> {
      IUser user = ISecurityContext.current().users().find(username);
      return user == null? null : new UserDTO(user);
    });
  }
  
  /**
   * @deprecated Use {@link ServiceUtilities#findUserDTO(String)} instead
   * @param username
   * @param application
   * @return user
   */
  @Deprecated(forRemoval = true, since = "9.4")
  public static UserDTO findUserDTO(final String username, final IApplication application) {
    return findUserDTO(username);
  }

  /**
   * @deprecated use {@link ServiceUtilities#findAllRoleDTO()} instead
   * @param app
   * @return list of {@link UserDTO}
   */
  @Deprecated(forRemoval = true, since = "9.4")
  public static List<RoleDTO> findAllRoleDTO(IApplication app) {
    return findAllRoleDTO();
  }
  
  public static List<RoleDTO> findAllRoleDTO() {
    return Sudo.get(() -> {
      return CollectionUtils.emptyIfNull(ISecurityContext.current().roles().all())
          .stream()
          .filter(role -> role.getProperty(AdditionalProperty.HIDE.toString()) == null)
          .map(role -> new RoleDTO(role))
          .collect(Collectors.toList());
    });
  }

  public static SecurityMemberDTO findSecurityMemberByName(String securityMemberName) {
    if (securityMemberName.startsWith("#")) {
      return findSecurityUserByName(securityMemberName.replace("#", ""));
    } 
    return findSecurityRoleByName(securityMemberName);
  }

  private static SecurityMemberDTO findSecurityUserByName(String securityMemberName) {
    IUser findUser = findUser(securityMemberName);
    return findUser == null ? null : new SecurityMemberDTO(findUser);
  }

  private static SecurityMemberDTO findSecurityRoleByName(String securityMemberName) {
    List<RoleDTO> roles = findAllRoleDTO().stream()
                        .filter(role -> StringUtils.equalsIgnoreCase(role.getName(), securityMemberName))
                        .collect(Collectors.toList());
    return SecurityMemberDTOMapper.mapFromRoleDTOs(roles).stream().findFirst().orElse(null);
  }
}