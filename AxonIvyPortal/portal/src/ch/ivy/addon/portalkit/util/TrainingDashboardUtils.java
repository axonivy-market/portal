package ch.ivy.addon.portalkit.util;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

/**
 * Utility class for training dashboard functionality
 * 
 * This class implements the training dashboard feature that automatically
 * displays a sample dashboard with portal user examples for first-time users
 * when the Portal User Examples project is installed.
 * 
 * Implementation Flow:
 * 1. User Login
 * 2. Check: Portal User Example project installed?
 *    - No → Normal portal homepage
 *    - Yes → Check: is_first_login = true?
 *      - No → Normal portal homepage  
 *      - Yes → Training dashboard
 *        - On completion/dismissal → Set is_first_login = false
 * 
 * @since 13.1.0
 */
public class TrainingDashboardUtils {

  private static final String PORTAL_USER_EXAMPLE_PROJECT_NAME = "portal-user-examples";
  private static final String TRAINING_DASHBOARD_PROCESS_SIGNATURE = "portalGetTraningDashboard()";

  private TrainingDashboardUtils() {}
  /**
   * Check if Portal User Example project is installed in the current security context
   * 
   * @return true if the project is installed and active
   */
  public static boolean isPortalUserExampleProjectInstalled() {
    boolean isInstalled = Sudo.get(() -> {
      return IApplicationRepository.of(ISecurityContext.current()).all().stream()
          .flatMap(app -> app.getProcessModels().stream())
          .anyMatch(pm -> PORTAL_USER_EXAMPLE_PROJECT_NAME.equals(pm.getName())
              && pm.getActivityState() == ch.ivyteam.ivy.application.ActivityState.ACTIVE);
    });
    
    Ivy.log().info("Portal User Examples project installed: {0}", isInstalled);
    return isInstalled;
  }/**
   * Check if current user has first login flag set to true
   * 
   * @return true if user is logging in for the first time
   */
  public static boolean isFirstLogin() {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser == null) {
      Ivy.log().info("No current user found");
      return false;
    }

    String firstLoginFlag = currentUser.getProperty(UserProperty.PORTAL_IS_FIRST_LOGIN);
    boolean isFirst = "true".equalsIgnoreCase(firstLoginFlag);
    
    Ivy.log().info("User {0} - first login flag: {1}, result: {2}", 
        currentUser.getName(), firstLoginFlag, isFirst);
    
    return isFirst;
  }

  /**
   * Set the first login flag for current user
   * 
   * @param isFirstLogin true for first login, false otherwise
   */
  public static void setFirstLogin(boolean isFirstLogin) {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser != null) {
      currentUser.setProperty(UserProperty.PORTAL_IS_FIRST_LOGIN, String.valueOf(isFirstLogin));
    }
  }
  /**
   * Initialize first login flag for new users This should be called during user creation or first authentication
   * 
   * @param user the user to initialize
   */
  public static void initializeFirstLoginFlag(IUser user) {
    if (user != null && StringUtils.isBlank(user.getProperty(UserProperty.PORTAL_IS_FIRST_LOGIN))) {
      Ivy.log().info("Initializing first login flag for user: {0}", user.getName());
      user.setProperty(UserProperty.PORTAL_IS_FIRST_LOGIN, "true");
    } else if (user != null) {
      Ivy.log().info("User {0} already has first login flag: {1}", 
          user.getName(), user.getProperty(UserProperty.PORTAL_IS_FIRST_LOGIN));
    }
  }/**
   * Check if training dashboard should be shown based on both conditions
   * 
   * @return true if both Portal User Example project is installed and user is first time login
   */  public static boolean shouldShowTrainingDashboard() {
    boolean projectInstalled = isPortalUserExampleProjectInstalled();
    boolean firstLogin = isFirstLogin();
    
    Ivy.log().info("Training Dashboard Check - Project installed: {0}, First login: {1}", projectInstalled, firstLogin);
    
    boolean result = projectInstalled && firstLogin;
    Ivy.log().info("Training Dashboard shouldShow result: {0}", result);
    return result;
  }

  /**
   * Mark training as completed and redirect to normal homepage   */  public static void completeTraining() {
    Ivy.log().info("Completing training for user");
    setFirstLogin(false);
    // Clear dashboard cache to ensure training dashboard is no longer included
    DashboardUtils.updateDashboardCache();
    Ivy.log().info("Training completed for user, dashboard cache cleared");
  }

  /**
   * Reset training state for testing purposes - this will make the training dashboard appear again
   */
  public static void resetTrainingForTesting() {
    Ivy.log().info("Resetting training state for current user");
    setFirstLogin(true);
    DashboardUtils.updateDashboardCache();
    Ivy.log().info("Training state reset, dashboard cache cleared");
  }/**
   * Handle homepage navigation logic based on training dashboard conditions
   * This method should be called during homepage determination to check
   * if training dashboard should be shown instead of normal homepage
   * 
   * @return true if training dashboard was shown, false if normal flow should continue
   */
  public static boolean handleHomepageNavigation() {
    if (shouldShowTrainingDashboard()) {
      Ivy.log().info("Attempting to navigate to training dashboard");
      try {
        PortalNavigator.navigateToTrainingDashboard();
        Ivy.log().info("Successfully navigated to training dashboard");
        return true;
      } catch (Exception e) {
        Ivy.log().warn("Failed to navigate to training dashboard, falling back to normal homepage", e);
        return false;
      }
    } else {
      Ivy.log().info("Training dashboard conditions not met, using normal homepage");
    }
    return false;
  }

  /**
   * Check if training dashboard is available (Portal User Examples project installed)
   * This method can be used to determine if training features should be visible in UI
   * 
   * @return true if training dashboard functionality is available
   */
  public static boolean isTrainingDashboardAvailable() {
    return isPortalUserExampleProjectInstalled();
  }

  /**
   * Load training dashboard from Portal User Examples project
   * This method calls the training dashboard process to retrieve the dashboard configuration
   * 
   * @return Dashboard object or null if not available
   */
  @SuppressWarnings("unchecked")
  public static Object loadTrainingDashboard() {
    try {
      return IvyAdapterService.startSubProcessInSecurityContext(
          TRAINING_DASHBOARD_PROCESS_SIGNATURE, 
          java.util.HashMap.class.cast(new java.util.HashMap<>())
      );
    } catch (Exception e) {
      Ivy.log().error("Error loading training dashboard from Portal User Examples project", e);
      return null;
    }
  }

  /**
   * Reset user training state - useful for testing or administrative purposes
   * 
   * @param user the user to reset
   */
  public static void resetTrainingState(IUser user) {
    if (user != null) {
      user.setProperty(UserProperty.PORTAL_IS_FIRST_LOGIN, "true");
    }
  }
  /**
   * Get training completion status for current user
   * 
   * @return true if user has completed training (not first login)
   */
  public static boolean isTrainingCompleted() {
    return !isFirstLogin();
  }

  /**
   * Testing utility method to force the training dashboard to appear for the current user
   * This method resets the first login flag and clears dashboard cache
   */
  public static void forceShowTrainingDashboard() {
    Ivy.log().info("TESTING: Forcing training dashboard to appear for current user");
    setFirstLogin(true);
    DashboardUtils.updateDashboardCache();
    Ivy.log().info("TESTING: Training dashboard should now appear. First login flag set to true, cache cleared");
  }
}
