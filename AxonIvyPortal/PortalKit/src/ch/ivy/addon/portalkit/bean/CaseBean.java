package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean(name = "caseBean")
public class CaseBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";

  /**
   * Get correspondence icon classes and color class for case state
   * 
   * @param state CaseState
   * @return css classes for case state
   */
  public String getCaseStateIcon(CaseState state) {
    if(state == null)  {
      return "";
    }
    switch(state) {
      case CREATED:
      case RUNNING:
        return "icon ivyicon-hourglass case-state-in-progress";
      case DONE:
        return "icon ivyicon-check-circle-1 case-state-done";
      case DESTROYED:
      case ZOMBIE:
        return "icon ivyicon-alert-circle case-state-zombie-destroyed";
      default: 
        return "";
    }
      
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

  public void backToCasesList() {
    String friendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CaseListPage.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = OPEN_CASES_LIST;
    }
    String requestPath = ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      TaskUtils.updateTaskStartedAttribute(false);
      PortalNavigator.redirect(requestPath);
    }
  }

  public boolean isCaseOwnerEnabled() {
    return new GlobalSettingService().isCaseOwnerEnabled();
  }
}
