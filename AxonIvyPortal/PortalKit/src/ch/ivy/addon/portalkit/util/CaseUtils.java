package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.PORTAL_USE_CUSTOM_FIELD_FOR_HIDDEN_TASK_CASE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IProcessStart;

public final class CaseUtils {

  /** default key to store case details process start link in ICase object */
  public static final String CASE_DETAIL_PROCESS = "CASE_DETAIL_PROCESS";
  private static final String HIDE = "HIDE";

  private CaseUtils() {
  }
  
  public static ICase findCase(long caseId) {
    return IvyExecutor.executeAsSystem(() -> Ivy.wf().findCase(caseId));
  }
  
  /**
   * Get the case Details process start link by key CASE_DETAILS_PROCESS in additional property
   * 
   * @param iCase ICase object
   * @return String the start link of case details
   */
  public static String getCaseDetailProcess(ICase iCase) {
    if (iCase == null || iCase.getAdditionalProperty(CASE_DETAIL_PROCESS) == null) {
      return "";
    }
    return iCase.getAdditionalProperty(CASE_DETAIL_PROCESS);
  }

  /**
   * Set case details process for case
   * 
   * @param iCase ICase need to set addition field
   * @param value String value to set in case details process
   * @return boolean true is set success, other wise it return false, execute under system permission
   */
  public static boolean setCaseDetailsProcess(final ICase iCase, final String value) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> {
        iCase.setAdditionalProperty(CASE_DETAIL_PROCESS, value);
        return true;
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }

  /**
   * Sets the "HIDE" additional property to the given case to hide it in case list of Portal.
   * 
   * @param iCase
   */
  public static void setHidePropertyToHideInPortal(ICase iCase) {
    boolean isUseCustomFieldForHiddenTaskCase = Boolean.parseBoolean(Ivy.var().get(PORTAL_USE_CUSTOM_FIELD_FOR_HIDDEN_TASK_CASE));
    if (isUseCustomFieldForHiddenTaskCase) {
      iCase.customFields().stringField(PortalConstants.HIDDEN_TASK_CASE_FIELD_NAME).set(HIDE);
    } else {
      iCase.setAdditionalProperty(HIDE, HIDE);
    }
  }

  /**
   * Removes the "HIDE" additional property to the given case to display it in case list of Portal.
   * 
   * @param iCase
   */
  public static void removeHidePropertyToDisplayInPortal(ICase iCase) {
    boolean isUseCustomFieldForHiddenTaskCase = Boolean.parseBoolean(Ivy.var().get(PORTAL_USE_CUSTOM_FIELD_FOR_HIDDEN_TASK_CASE));
    if (isUseCustomFieldForHiddenTaskCase) {
      iCase.customFields().stringField(PortalConstants.HIDDEN_TASK_CASE_FIELD_NAME).delete();
    } else {
      iCase.setAdditionalProperty(HIDE, null);
    }
  }

  public static String getProcessStartUriWithCaseParameters(ICase iCase, String requestPath) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    String urlParameters = "?caseId=" + iCase.getId();
    try {
      return collector.findLinkByFriendlyRequestPath(requestPath) + urlParameters;
    } catch (Exception e) {
      Ivy.log().error(e);
      IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(requestPath);
      return RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString()
          + urlParameters;
    }
  }
  
  public static List<INote> findNotes(ICase iCase, boolean excludeSystemNotes) {
    Objects.requireNonNull(iCase, "Case must not be null");
    List<INote> notes = iCase.getNotes();
    if (excludeSystemNotes) {
      notes = notes.stream()
          .filter(n -> !StringUtils.equals(n.getWritterName(), ISecurityConstants.SYSTEM_USER_NAME))
          .collect(Collectors.toList());
    }
    return new ArrayList<>(notes);
  }
}