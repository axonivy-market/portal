package com.axonivy.portal.components.publicapi;

import java.util.HashMap;
import java.util.Map;

import com.axonivy.portal.components.enums.SessionAttribute;
import com.axonivy.portal.components.generic.navigation.BaseNavigator;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.StandardProcessType;

/**
 * Portal API for navigation not in iFrame
 *
 */
public final class PortalNavigatorAPI extends BaseNavigator {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private static final String PORTAL_PROCESS_START_CASE_DETAIL = "Start Processes/PortalStart/CaseDetailsLink.ivp";
  private static final String PORTAL_PROCESS_START_TASK_DETAIL = "Start Processes/PortalStart/TaskDetailsLink.ivp";


  private PortalNavigatorAPI() {}

  /**
   * Navigate to Portal home
   */
  public static void navigateToPortalHome() {
    navigateByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME, new HashMap<>());
  }

  /**
   * Navigate to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task
   * list or task details or global search NOTES: is only used for the task not started in Portal IFrame
   */
  public static void navigateToPortalEndPage() {
    String customizePortalEndPage = getRelativeLink(StandardProcessType.DefaultEndPage);
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    redirectURL(String.format("%s?endedTaskId=%s", customizePortalEndPage, Ivy.wfTask().getId()));
  }

  /**
   * Build url to case details page of case id
   * @deprecated use {@link #buildUrlToPortalCaseDetailsPage(String)}
   * @param caseId
   * @return Absolute url to case details page of case id
   */
  @Deprecated
  public static String buildUrlToPortalCaseDetailsPageById(Long caseId) {
    String id = Long.toString(caseId);
    ICase caze = Sudo.get(() -> Ivy.wf().findCase(caseId));
    return buildUrlToPortalCaseDetailsPageByUUID(caze != null ? caze.uuid() : id);
  }

  /**
   * Build url to task details page of task id
   * @deprecated use {@link #buildUrlToPortalTaskDetailsPage(String)}
   * @param taskId
   * @return Absolute url to task details page of task id
   */
  @Deprecated
  public static String buildUrlToPortalTaskDetailsPageById(Long taskId) {
    String id = Long.toString(taskId);
    ITask task = Sudo.get(() -> Ivy.wf().findTask(taskId));
    return buildUrlToPortalTaskDetailsPageByUUID(task != null ? task.uuid() : id);
  }
  
  /**
   * Build url to case details page of task uuid
   * @deprecated use {@link #buildUrlToPortalCaseDetailsPage(String)}
   * @param uuid
   * @return Absolute url to case details page of case uuid
   */
  @Deprecated
  public static String buildUrlToPortalCaseDetailsPageByUUID(String uuid) {
    Map<String, String> param = new HashMap<>();
    param.put("uuid", uuid);
    return buildAbsoluteUrl(PORTAL_PROCESS_START_CASE_DETAIL, param);
  }

  /**
   * Build url to task details page of task uuid
   * @deprecated use {@link #buildUrlToPortalTaskDetailsPage(String)}
   * @param uuid
   * @return Absolute url to task details page of task uuid
   */
  @Deprecated
  public static String buildUrlToPortalTaskDetailsPageByUUID(String uuid) {
    Map<String, String> param = new HashMap<>();
    param.put("uuid", uuid);
    return buildAbsoluteUrl(PORTAL_PROCESS_START_TASK_DETAIL, param);
  }
  
  /**
   * Build url to case details page of task uuid
   * 
   * @param uuid
   * @return Absolute url to case details page of case uuid
   */
  public static String buildUrlToPortalCaseDetailsPage(String uuid) {
    ICase caze = Sudo.get(() -> Ivy.wf().findCase(uuid));
    return (caze != null && caze.getDetailLink() != null) ? caze.getDetailLink().getAbsolute() : "";
  }

  /**
   * Build url to task details page of task uuid
   * 
   * @param uuid
   * @return Absolute url to task details page of task uuid
   */
  public static String buildUrlToPortalTaskDetailsPage(String uuid) {
    ITask task = Sudo.get(() -> Ivy.wf().findTask(uuid));
    return (task != null && task.getDetailLink() != null) ? task.getDetailLink().getAbsolute() : "";
  }
}
