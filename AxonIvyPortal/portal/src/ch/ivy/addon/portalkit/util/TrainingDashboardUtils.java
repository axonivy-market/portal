package ch.ivy.addon.portalkit.util;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

/**
 * Utility class for training dashboard functionality
 */
public class TrainingDashboardUtils {

  private static final String PORTAL_USER_EXAMPLE_PROJECT_NAME = "portal-user-examples";

  private TrainingDashboardUtils() {}

  /**
   * Check if Portal User Example project is installed in the current security context
   * 
   * @return true if the project is installed and active
   */
  public static boolean isPortalUserExampleProjectInstalled() {
    return Sudo.get(() -> {
      return IApplicationRepository.of(ISecurityContext.current()).all().stream()
          .flatMap(app -> app.getProcessModels().stream())
          .anyMatch(pm -> PORTAL_USER_EXAMPLE_PROJECT_NAME.equals(pm.getName())
              && pm.getActivityState() == ch.ivyteam.ivy.application.ActivityState.ACTIVE);
    });
  }

  /**
   * Check if current user has first login flag set to true
   * 
   * @return true if user is logging in for the first time
   */
  public static boolean isFirstLogin() {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser == null) {
      return false;
    }

    String firstLoginFlag = currentUser.getProperty(UserProperty.IS_FIRST_LOGIN);
    return "true".equalsIgnoreCase(firstLoginFlag);
  }

  /**
   * Set the first login flag for current user
   * 
   * @param isFirstLogin true for first login, false otherwise
   */
  public static void setFirstLogin(boolean isFirstLogin) {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser != null) {
      currentUser.setProperty(UserProperty.IS_FIRST_LOGIN, String.valueOf(isFirstLogin));
    }
  }

  /**
   * Initialize first login flag for new users This should be called during user creation or first authentication
   * 
   * @param user the user to initialize
   */
  public static void initializeFirstLoginFlag(IUser user) {
    if (user != null && StringUtils.isBlank(user.getProperty(UserProperty.IS_FIRST_LOGIN))) {
      user.setProperty(UserProperty.IS_FIRST_LOGIN, "true");
    }
  }

  /**
   * Check if training dashboard should be shown based on both conditions
   * 
   * @return true if both Portal User Example project is installed and user is first time login
   */
  public static boolean shouldShowTrainingDashboard() {
    return isPortalUserExampleProjectInstalled() && isFirstLogin();
  }

  /**
   * Mark training as completed and redirect to normal homepage
   */
  public static void completeTraining() {
    setFirstLogin(false);
  }
}
