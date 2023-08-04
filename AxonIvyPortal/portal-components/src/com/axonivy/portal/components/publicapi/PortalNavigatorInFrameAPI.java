package com.axonivy.portal.components.publicapi;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.generic.navigation.BaseNavigator;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Portal API for navigation in iFrame
 *
 */
public final class PortalNavigatorInFrameAPI extends BaseNavigator {
  private PortalNavigatorInFrameAPI() {}

  private static final String PORTAL_PROCESS_START_CASE_DETAIL = "Start Processes/PortalStart/CaseDetailsInIFrame.ivp";
  private static final String PORTAL_PROCESS_START_TASK_DETAIL = "Start Processes/PortalStart/TaskDetailsInIframe.ivp";

  /**
   * Navigate to target url
   * @param url target url
   */ 
  public static void navigateToUrl(String url) {
    String statement = "parent.redirectToUrlCommand([{name: 'url', value: '" + URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
    PrimeFaces.current().executeScript(statement);
  }
  
  /**
   * Navigate to portal home
   */
  public static void navigateToPortalHome() {
    navigateToUrl(Ivy.html().applicationHomeRef());
  }

  /**
   * Navigate to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task list or task details or global search
   */
  public static void navigateToPortalEndPage() {
    String statement = "parent.redirectToEndPageCommand([{name: 'taskId', value: " + Ivy.wfTask().getId() + "}]);";
    PrimeFaces.current().executeScript(statement);
  }

  /**
   * Build url to case details page of case id
   * 
   * @param caseId
   * @return Absolute url to case details page of case id
   */
  public static String buildUrlToPortalCaseDetailsPage(String caseId) {
    Map<String, String> param = new HashMap<>();
    param.put("caseId", caseId);
    return buildAbsoluteUrl(PORTAL_PROCESS_START_CASE_DETAIL, param);
  }

  /**
   * Build url to task details page of task id
   * 
   * @param taskId
   * @return Absolute url to task details page of task id
   */
  public static String buildUrlToPortalTaskDetailsPage(String taskId) {
    Map<String, String> param = new HashMap<>();
    param.put("taskDetailsId", taskId);
    return buildAbsoluteUrl(PORTAL_PROCESS_START_TASK_DETAIL, param);
  }
}
