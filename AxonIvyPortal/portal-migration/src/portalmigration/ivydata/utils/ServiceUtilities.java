package portalmigration.ivydata.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import portalmigration.dto.UserDTO;
import portalmigration.util.IvyExecutor;

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
  public static List<IRole> findAllRoles(IApplication app) {
    requireNonNull(app);

    List<IRole> roles = new ArrayList<>(app.getSecurityContext().getRoles());
    roles.removeIf(role -> role.getProperty(AdditionalProperty.HIDE.toString()) != null);
    roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
    return roles;
  }

  public static List<IProcessModelVersion> getActiveReleasedPmvs(IApplication app) {
    requireNonNull(app);

    return app.getProcessModels().stream().filter(pm -> pm.getActivityState() == ActivityState.ACTIVE)
        .map(IProcessModel::getReleasedProcessModelVersion)
        .filter(pmv -> pmv != null && pmv.getActivityState() == ActivityState.ACTIVE).collect(Collectors.toList());
  }


  public static IUser findUser(String username) {
    return IvyExecutor.executeAsSystem(() -> {
      return findUser(username, Ivy.wf().getApplication());
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

}
