package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.ProcessViewerUtils;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@RequestScoped
public class CaseActionBean implements Serializable {

  private static final long serialVersionUID = 7468665222036995531L;
  private static final String START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE = "Start Processes/PortalStart/showAdditionalCaseDetails.ivp";
  private boolean isShowCaseDetails;

  @PostConstruct
  public void initCaseActions() {
    isShowCaseDetails = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_CASE_DETAILS);
  }

  public String getAdditionalCaseDetailsPageUri(ICase iCase) {
    String additionalCaseDetailsPageUri = StringUtils.EMPTY;
    if (isExpressCase(iCase)) {
      ProcessStartCollector processStartCollector =  new ProcessStartCollector();
      additionalCaseDetailsPageUri = processStartCollector.findExpressBusinessViewStartLink() + "?caseId=" + iCase.getId();
    } else {
      additionalCaseDetailsPageUri = iCase.customFields().textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull();
      if (StringUtils.isEmpty(additionalCaseDetailsPageUri)) {
        Map<String, String> params = new HashMap<>();
        params.put("caseId", String.valueOf(iCase.getId()));
        additionalCaseDetailsPageUri = PortalNavigator.buildUrlByKeyword("showAdditionalCaseDetails", START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE, params);
      } else {
        additionalCaseDetailsPageUri = additionalCaseDetailsPageUri + "&embedInFrame";
      }
    }
    return additionalCaseDetailsPageUri;
  }

  public String getProcessViewerPageUri(ICase selectedCase) {
    return ProcessViewerUtils.getStartProcessViewerPageUri(selectedCase);
  }

  private boolean isExpressCase(ICase iCase) {
    return BooleanUtils.toBoolean(iCase.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrNull());
  }

  public boolean showProcessOverviewLink(ICase iCase) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_PROCESS_INFORMATION)
        && !isExpressCase(iCase) && iCase.isBusinessCase();
  }

  public String getProcessOverviewPageUri(ICase iCase) {
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
    return ProcessViewerUtils.isShowProcessViewer(caze);
  }
}
