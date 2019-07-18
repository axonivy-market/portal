package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.fieldset.Fieldset;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomField;
import ch.ivyteam.ivy.workflow.custom.field.ICustomNumberField;

@ManagedBean(name = "caseBean")
public class CaseBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final String OPEN_CASE_ITEM_DETAILS = "Start Processes/PortalStart/startPortalCaseDetails.ivp";

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

  public void navigate(ICase iCase) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("startPortalCaseDetails.ivp");
    if (StringUtils.isEmpty(customizePortalFriendlyRequestPath)) {
      customizePortalFriendlyRequestPath = OPEN_CASE_ITEM_DETAILS;
    }
    redirect(CaseUtils.getProcessStartUriWithCaseParameters(iCase, customizePortalFriendlyRequestPath));
  }

  public void redirect(String url) {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }
  
  public List<ICustomField<?>> getCaseCustomFields(ICase iCase) {
    List<ICustomField> caseCustomFields = new ArrayList<>();
    List<ICustomField<?>> customFields = iCase.customFields().all();
    if (customFields != null && !customFields.isEmpty()) {
      for (ICustomField<?> fields : customFields) {
        ICustomField<ICustomNumberField> test = null;
        caseCustomFields.add(fields);
      }
    }
    
    return null;
  }
  
}
