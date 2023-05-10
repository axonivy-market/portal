package portalmigration.ivydata.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import portalmigration.dto.RoleDTO;
import portalmigration.dto.SecurityMemberDTO;
import portalmigration.ivydata.mapper.SecurityMemberDTOMapper;
import portalmigration.util.IvyExecutor;

public class ServiceUtilities {

  private ServiceUtilities() {}

  private static void requireNonNull(IApplication app) {
    Objects.requireNonNull(app, "The application must not be null");
  }

  public static List<IProcessModelVersion> getActiveReleasedPmvs(IApplication app) {
    requireNonNull(app);

    return app.getProcessModels().stream().filter(pm -> pm.getActivityState() == ActivityState.ACTIVE)
        .map(IProcessModel::getReleasedProcessModelVersion)
        .filter(pmv -> pmv != null && pmv.getActivityState() == ActivityState.ACTIVE).collect(Collectors.toList());
  }

  public static List<RoleDTO> findAllRoleDTO() {
    return IvyExecutor.executeAsSystem(() -> {
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

  public static IUser findUser(String username) {
    return IvyExecutor.executeAsSystem(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }
}
