package ch.ivy.addon.portalkit.bean;

import static ch.ivyteam.ivy.security.IPermission.CASE_DESTROY;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.vo.CaseVO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@ManagedBean(name = "caseBean")
public class CaseBean implements Serializable {
  private static final long serialVersionUID = 1L;

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

  public String getState(RemoteCase iCase) {
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

  public boolean isCaseAbleToAddNote(RemoteCase iCase) {
    return Objects.nonNull(iCase) && iCase.getState() == CaseState.RUNNING;
  }

  public boolean isCaseAbleToDelete(CaseVO caseVO) {
    return Objects.nonNull(caseVO) && isCaseRunning(caseVO) && isSessionUserHasDeletePermission();
  }

  private boolean isCaseRunning(CaseVO caseVO) {
    return CaseState.RUNNING.toString().equalsIgnoreCase(caseVO.getStatus());
  }

  private boolean isSessionUserHasDeletePermission() {
    IWorkflowSession ivySession = Ivy.session();
    ISecurityDescriptor securityDescriptor = Ivy.request().getApplication().getSecurityDescriptor();
    return ivySession.hasPermission(securityDescriptor, CASE_DESTROY);
  }

}
