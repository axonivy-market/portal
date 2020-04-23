package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean(name = "caseBean")
public class CaseBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String OPEN_CASE_ITEM_DETAILS = "Start Processes/PortalStart/CaseDetailsPage.ivp";
  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";

  /**
   * Get the font-awesome class of specified CaseState
   * 
   * @param state CaseState
   * @return String CSS class of the caseState
   */
  public String getCaseStateIcon(CaseState state) {
    if (CaseState.CREATED.equals(state)) {
      return "fa fa-file-o";
    } else if (CaseState.DESTROYED.equals(state)) {
      return "fa fa-times";
    } else if (CaseState.DONE.equals(state)) {
      return "fa fa-check";
    } else if (CaseState.RUNNING.equals(state)) {
      return "fa fa-spinner";
    }

    return "fa-user-secret";
  }

  /**
   * Get the state of case
   * 
   * @param iCase to get the state
   * @return the state of case
   */
  public String getState(ICase iCase) {
    if (iCase == null) {
      return "";
    }
    return getDisplayState(iCase.getState());
  }

  private String getDisplayState(CaseState caseState) {
    if (caseState == CaseState.CREATED || caseState == CaseState.RUNNING) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/RUNNING");
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + caseState);
  }

  public void navigateToCaseDetails(ICase iCase) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CaseDetailsPage.ivp");
    if (StringUtils.isEmpty(customizePortalFriendlyRequestPath)) {
      customizePortalFriendlyRequestPath = OPEN_CASE_ITEM_DETAILS;
    }
    PortalNavigator.redirect(CaseUtils.getProcessStartUriWithCaseParameters(iCase, customizePortalFriendlyRequestPath));
  }

  public void backToCasesList() {
    String friendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CaseListPage.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = OPEN_CASES_LIST;
    }
    String requestPath = ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(Ivy.wf().getApplication(), friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      PortalNavigator.redirect(requestPath);
    }
  }

}
