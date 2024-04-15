package com.axonivy.portal.components.publicapi;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

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
  private static final String PORTAL_PASSWORD_RESET = "Start Processes/PortalStart/PasswordResetPage.ivp";


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
   * 
   * @param caseId
   * @return Absolute url to case details page of case id
   */
  public static String buildUrlToPortalCaseDetailsPageById(Long caseId) {
    String id = Long.toString(caseId);
    ICase caze = Sudo.get(() -> Ivy.wf().findCase(caseId));
    return buildUrlToPortalCaseDetailsPageByUUID(caze != null ? caze.uuid() : id);
  }

  /**
   * Build url to task details page of task id
   * 
   * @param taskId
   * @return Absolute url to task details page of task id
   */
  public static String buildUrlToPortalTaskDetailsPageById(Long taskId) {
    String id = Long.toString(taskId);
    ITask task = Sudo.get(() -> Ivy.wf().findTask(taskId));
    return buildUrlToPortalTaskDetailsPageByUUID(task != null ? task.uuid() : id);
  }
  
  /**
   * Build url to case details page of task uuid
   * 
   * @param uuid
   * @return Absolute url to case details page of case uuid
   */
  public static String buildUrlToPortalCaseDetailsPageByUUID(String uuid) {
    ICase caze = Sudo.get(() -> Ivy.wf().findCase(uuid));
    return (caze != null && caze.getDetailLink() != null) ? caze.getDetailLink().getAbsolute() : "";
  }

  /**
   * Build url to task details page of task uuid
   * 
   * @param uuid
   * @return Absolute url to task details page of task uuid
   */
  public static String buildUrlToPortalTaskDetailsPageByUUID(String uuid) {
    ITask task = Sudo.get(() -> Ivy.wf().findTask(uuid));
    return (task != null && task.getDetailLink() != null) ? task.getDetailLink().getAbsolute() : "";
  }

  /**
   * Build url to the Reset Password page of a specific user.
   * 
   * @param token the token to validate user permission to access the page
   * @param username username of the taget user
   * @return Absolute url to the Reset Password page of a specific user.
   */
  public static String getPasswordResetUrl(String token, String username) {
    Map<String, String> params = new HashMap<>();
    params.put("token", token);
    params.put("username", username);
    return buildUrl(PORTAL_PASSWORD_RESET, params);
  }

  private static String buildUrl(String friendlyRequestPath, Map<String, String> params) {
    String requestPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isEmpty(requestPath)) {
      return StringUtils.EMPTY;
    }
    String paramStr = params.entrySet().stream().map(e -> {
      return e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.ISO_8859_1);
    }).collect(Collectors.joining("&"));
    return requestPath + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY);
  }
}
