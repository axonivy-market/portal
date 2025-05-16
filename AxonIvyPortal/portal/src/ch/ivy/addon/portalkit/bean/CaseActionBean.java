package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.enums.PortalPermission.SHOW_CASE_DETAILS;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.axonivy.portal.util.BusinessDetailsUtils;

import ch.ivy.addon.portal.generic.bean.UserMenuBean;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PortalProcessViewerUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@RequestScoped
public class CaseActionBean implements Serializable {

  private static final long serialVersionUID = 7468665222036995531L;
  private boolean isShowCaseDetails;

  @PostConstruct
  public void initCaseActions() {
    isShowCaseDetails = PermissionUtils.hasPortalPermission(SHOW_CASE_DETAILS);
  }

  public String getAdditionalCaseDetailsPageUri(ICase iCase) {
    return BusinessDetailsUtils.getAdditionalCaseDetailsPageUri(iCase);
  }

  public String getProcessViewerPageUri(ICase selectedCase) {
    return PortalProcessViewerUtils.getStartProcessViewerPageUri(selectedCase);
  }

  public boolean showProcessOverviewLink(@SuppressWarnings("unused") ICase iCaze) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_PROCESS_INFORMATION);
  }

  public String getProcessOverviewPageUri(ICase iCase) {
    if (Objects.isNull(iCase) || Objects.isNull(iCase.getApplication()) || Objects.isNull(iCase.getProcessStart())) {
      return "#";
    }
    return PortalNavigator.buildProcessInfoUrl(iCase.getApplication().getName().concat("/")
        .concat(iCase.getProcessStart().getFullUserFriendlyRequestPath()));
  }

  public boolean canChangeName(ICase iCase) {
    return hasPermission(iCase, IPermission.CASE_WRITE_NAME);
  }

  public boolean canChangeDescription(ICase iCase) {
    return hasPermission(iCase, IPermission.CASE_WRITE_DESCRIPTION);
  }

  public boolean canDestroy(ICase iCase) {
    return hasPermission(iCase, IPermission.CASE_DESTROY);
  }

  private boolean hasPermission(ICase iCase, IPermission permission) {
    if (iCase == null || permission == null) {
      return false;
    }
    return PermissionUtils.hasPermission(permission);
  }

  public boolean isShowCaseDetails() {
    return isShowCaseDetails;
  }

  public void setShowCaseDetails(boolean isShowCaseDetails) {
    this.isShowCaseDetails = isShowCaseDetails;
  }

  public boolean showProcessViewer(ICase caze) {
    return ((UserMenuBean) ManagedBeans.get("userMenuBean")).isProcessViewerDisplayed(caze);
  }

  public boolean canPin(ICase caze) {
    if (caze == null) {
      return false;
    }
    return true;
  }

  public String getPinnedStyleClass(ICase caze) {
    return "aadditional-case-details-link action-step-item ui-menu-items"
        + (CaseUtils.isPinnedCase(caze) ? " color-destroy" : "");
  }

  public void markCaseAsPinned(ICase caze) {
    CaseUtils.markCaseAsPinned(caze);
  }

  public String getPinnedIcon(ICase caze) {
    return CaseUtils.isPinnedCase(caze) ? "option-action-icon si si-pin-bold" : "option-action-icon si si-pin";
  }

  public String getPinnedLabel(ICase caze) {
    return CaseUtils.isPinnedCase(caze) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/unpin")
        : Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/pin");
  }

}
