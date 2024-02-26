package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PortalProcessViewerUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.IvyConstants;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;

@SuppressWarnings("restriction")
@ManagedBean
@RequestScoped
public class CaseActionBean implements Serializable {

  private static final long serialVersionUID = 7468665222036995531L;
  private static final String START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE = "Start Processes/PortalStart/showAdditionalCaseDetails.ivp";
  private static final String FULL_RELATIVE_URL_FORMAT = "/%s/%s/%s";
  private static final String CASE_ID_PARAM = "caseId";
  private static final String EMBED_IN_FRAME_PARAM = "embedInFrame";
  private boolean isShowCaseDetails;

  @PostConstruct
  public void initCaseActions() {
    isShowCaseDetails = PermissionUtils.hasPortalPermission(PortalPermission.SHOW_CASE_DETAILS);
  }

  public String getAdditionalCaseDetailsPageUri(ICase iCase) {
    String additionalCaseDetailsPageUri = StringUtils.EMPTY;
    if (isExpressCase(iCase)) {
      additionalCaseDetailsPageUri = ExpressProcessService.getInstance().findExpressBusinessViewStartLink() + "?caseId="
          + iCase.getId();
    } else {
      additionalCaseDetailsPageUri = getBusinessDetailURLFromCustomField(iCase);
    }
    return additionalCaseDetailsPageUri;
  }

  private String getBusinessDetailURLFromCustomField(ICase iCase) {
    // Get additional case details page URI from the new custom field
    // businessDetails
    String additionalCaseDetailsPageUri = iCase.customFields()
        .stringField(com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS).getOrNull();

    // If cannot find additional case details page URI, find again with the old
    // custom field CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE
    if (StringUtils.isEmpty(additionalCaseDetailsPageUri)) {
      additionalCaseDetailsPageUri = iCase.customFields()
          .textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull();
    }

    if (StringUtils.isNotEmpty(additionalCaseDetailsPageUri)) {
      // Only adapt for relative paths URL (start with '/')
      if (additionalCaseDetailsPageUri.startsWith("/")) {
        // If found custom additional case details page URI
        // adapt it to format {process model}/{request path}
        additionalCaseDetailsPageUri = getProcessModelAndRequestPath(additionalCaseDetailsPageUri, iCase);

        // Add caseId and embedInFrame to params of the url
        additionalCaseDetailsPageUri = appendParamsToUrl(additionalCaseDetailsPageUri, iCase);

        // Append application and process prefix parts to the url
        additionalCaseDetailsPageUri = appendApplicationPartsToUrl(additionalCaseDetailsPageUri, iCase);
      }

    } else {
      // If cannot find custom additional case details page URI
      // Set the default additional case details page URI
      Map<String, String> params = new HashMap<>();
      params.put("caseId", String.valueOf(iCase.getId()));
      additionalCaseDetailsPageUri = PortalNavigator.buildUrlByKeyword("showAdditionalCaseDetails",
          START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE, params);
    }

    return additionalCaseDetailsPageUri;
  }

  private String getProcessModelAndRequestPath(String url, ICase iCase) {
    // get {process model}/{request path}?{params}
    String result = url.substring(url.indexOf(iCase.getProcessModel().getName()), url.length());
    return result;

  }

  private String appendParamsToUrl(String url, ICase iCase) {
    Map<String, String> params = UrlUtils.getParamsFromUrl(url);

    if (params.isEmpty()) {
      return url.concat("?" + CASE_ID_PARAM + "=" + iCase.getId()).concat("&" + EMBED_IN_FRAME_PARAM);
    }

    if (!params.containsKey(CASE_ID_PARAM) || params.get(CASE_ID_PARAM).isBlank()) {
      params.put(CASE_ID_PARAM, Long.toString(iCase.getId()));
    }

    if (!params.containsKey(EMBED_IN_FRAME_PARAM)) {
      params.put(EMBED_IN_FRAME_PARAM, "");
    }
    String result = url.substring(0, url.indexOf("?") + 1);

    result = UrlUtils.appendParamsToNonParamsUrl(params, result);

    if (result.endsWith("&")) {
      result = result.substring(0, result.length() - 1);
    }
    Ivy.log().error(result);
    return result;
  }

  private String appendApplicationPartsToUrl(String url, ICase iCase) {
    return String.format(FULL_RELATIVE_URL_FORMAT, iCase.getApplication().getName(), IvyConstants.REQUEST_PATH_PROCESS,
        url);
  }

  public String getProcessViewerPageUri(ICase selectedCase) {
    return PortalProcessViewerUtils.getStartProcessViewerPageUri(selectedCase);
  }

  private boolean isExpressCase(ICase iCase) {
    return BooleanUtils.toBoolean(iCase.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrNull());
  }

  public boolean showProcessOverviewLink(ICase iCase) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_PROCESS_INFORMATION)
        && !isExpressCase(iCase) && iCase.isBusinessCase();
  }

  public String getProcessOverviewPageUri(ICase iCase) {
    if (Objects.isNull(iCase) || Objects.isNull(iCase.getApplication()) || Objects.isNull(iCase.getProcessStart())) {
      return "#";
    }
    return PortalNavigator.buildProcessInfoUrl(
        iCase.getApplication().getName().concat("/").concat(iCase.getProcessStart().getFullUserFriendlyRequestPath()));
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
    return PortalProcessViewerUtils.isShowProcessViewer(caze);
  }
}
