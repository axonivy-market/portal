package com.axonivy.portal.components.publicapi;

import java.util.HashMap;
import java.util.Map;

import com.axonivy.portal.components.enums.SessionAttribute;
import com.axonivy.portal.components.generic.navigation.BaseNavigator;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.StandardProcessType;

/**
 * Portal API for navigation not in iFrame
 *
 */
public final class PortalNavigatorAPI extends BaseNavigator {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private static final String PORTAL_PROCESS_START_CASE_DETAIL = "Start Processes/PortalStart/CaseDetailsInIFrame.ivp";
  private static final String PORTAL_PROCESS_START_TASK_DETAIL = "Start Processes/PortalStart/TaskDetailsInIframe.ivp";


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

  /**
   * Navigate to Portal case details page by case id
   */
  public static void navigateToPortalCaseDetailsPage(String caseId) {
    Map<String, String> param = new HashMap<>();
    param.put("caseId", caseId);
    navigateByKeyword("CaseDetailsInIFrame.ivp", PORTAL_PROCESS_START_CASE_DETAIL, param);
  }

  /**
   * Navigate to Portal task details page by case id
   */
  public static void navigateToPortalTaskDetailsPage(String taskId) {
    Map<String, String> param = new HashMap<>();
    param.put("taskDetailsId", taskId);
    navigateByKeyword("TaskDetailsInIframe.ivp", PORTAL_PROCESS_START_TASK_DETAIL, param);
  }
}
