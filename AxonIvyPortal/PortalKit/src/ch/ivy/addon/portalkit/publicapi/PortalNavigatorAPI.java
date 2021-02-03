package ch.ivy.addon.portalkit.publicapi;

import java.util.HashMap;

import ch.ivy.addon.portal.generic.navigation.BaseNavigator;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.StandardProcessType;

/**
 * Portal API for navigation not in iFrame
 *
 */
public final class PortalNavigatorAPI extends BaseNavigator{
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private PortalNavigatorAPI() {}
  
  /**
   * Navigate to Portal home
   */
  public static void navigateToPortalHome() {
    navigateByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME, new HashMap<>());
  }

  /**
   * Navigate to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page:
   * task list or task details or global search NOTES: is only used for the task not started in Portal IFrame
   */
  public static void navigateToPortalEndPage() {
    String customizePortalEndPage = getRelativeLink(StandardProcessType.DefaultEndPage);
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    redirectURL(String.format("%s?endedTaskId=%s", customizePortalEndPage, Ivy.wfTask().getId()));
  }
}
