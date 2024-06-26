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
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.util.UserUtils;
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
  
  private static void requireNonNull(IApplication app) {
    Objects.requireNonNull(app, "The application must not be null");
  }

  
  public static List<IUser> findAllUsers() {
    return ISecurityContext.current()
      .users()
      .paged()
      .stream()
      .filter(user -> !StringUtils.equals(ISecurityConstants.SYSTEM_USER_NAME, user.getName()))
      .collect(Collectors.toList());
  }

  
  /**
   * Finds all of the roles in security context, except the roles have the HIDE property
   * @return roles
   */
  @SuppressWarnings("unchecked")
  public static List<IRole> findAllRoles() {
    Optional<Object> cacheValueOpt =
        IvyCacheService.getInstance().getSessionCacheValue(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT);
    if (cacheValueOpt.isPresent()) {
      return (List<IRole>) cacheValueOpt.get();
    }

    List<IRole> roles = new ArrayList<>(ISecurityContext.current().roles().all());
    roles.removeIf(role -> role.getProperty(AdditionalProperty.HIDE.toString()) != null);
    roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));

    IvyCacheService.getInstance().setSessionCache(ISecurityContext.current().getName(), IvyCacheIdentifier.ROLES_IN_SECURITY_CONTEXT, roles);
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
    IUser findUser = UserUtils.findUserByUsername(securityMemberName);
    return findUser == null ? null : new SecurityMemberDTO(findUser);
  }

  private static SecurityMemberDTO findSecurityRoleByName(String securityMemberName) {
    List<RoleDTO> roles = findAllRoleDTO().stream()
                        .filter(role -> StringUtils.equalsIgnoreCase(role.getName(), securityMemberName))
                        .collect(Collectors.toList());
    return SecurityMemberDTOMapper.mapFromRoleDTOs(roles).stream().findFirst().orElse(null);
  }
}