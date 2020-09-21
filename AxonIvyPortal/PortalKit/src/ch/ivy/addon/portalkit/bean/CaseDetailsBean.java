package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class CaseDetailsBean implements Serializable {

  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";

  private boolean isShowCaseDetails;
  private boolean isHideCaseDocument;
  private boolean isTaskStartedInDetails;
  private boolean showBackButton;
  private ICase selectedCase;
  
  private CaseActionBean caseActionBean;

  @PostConstruct
  public void init() {
    isShowCaseDetails = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_CASE_DETAILS);
    caseActionBean = ManagedBeans.get("caseActionBean");
  }
  
  public void create(ICase selectedCase, boolean showBackButton) {
    this.selectedCase = selectedCase;
    this.showBackButton = showBackButton;
    this.isTaskStartedInDetails = BooleanUtils.toBooleanDefaultIfNull((Boolean) Ivy.session().getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()), false);
  }

  public void backToCasesList() {
    String friendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CaseListPage.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = OPEN_CASES_LIST;
    }
    String requestPath = ProcessStartUtils.findRelativeUrlByProcessStartFriendlyRequestPath(Ivy.wf().getApplication(), friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      TaskUtils.updateTaskStartedAttribute(false);
      PortalNavigator.redirect(requestPath);
    }
  }
  
  public boolean showDestroyLink(ICase caseItem) {
    return caseActionBean.canDestroy(caseItem)
        && caseItem.getState() != CaseState.DONE
        && caseItem.getState() != CaseState.DESTROYED;
  }

  /**
   * Get the latest configuration of HIDE_CASE_DOCUMENT in GlobalSettingService
   * If null or empty, will return false
   */
  public void getHideCaseDocumentConfiguration() {
    isHideCaseDocument = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_CASE_DOCUMENT.toString());
  }

  public boolean isHideCaseDocument() {
    return isHideCaseDocument;
  }

  public void setHideCaseDocument(boolean isHideCaseDocument) {
    this.isHideCaseDocument = isHideCaseDocument;
  }

  public boolean isShowCaseDetails() {
    return isShowCaseDetails;
  }

  public void setShowCaseDetails(boolean isShowCaseDetails) {
    this.isShowCaseDetails = isShowCaseDetails;
  }

  public boolean isTaskStartedInDetails() {
    return isTaskStartedInDetails;
  }

  public void setTaskStartedInDetails(boolean isTaskStartedInDetails) {
    this.isTaskStartedInDetails = isTaskStartedInDetails;
  }

  public boolean isShowBackButton() {
    return showBackButton;
  }

  public void setShowBackButton(boolean showBackButton) {
    this.showBackButton = showBackButton;
  }

  public ICase getSelectedCase() {
    return selectedCase;
  }

  public void setSelectedCase(ICase selectedCase) {
    this.selectedCase = selectedCase;
  }

}
