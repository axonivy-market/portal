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

import com.axonivy.portal.components.publicapi.IWebStartableAPI;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PortalProcessViewerUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

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
      additionalCaseDetailsPageUri =
          ExpressProcessService.getInstance().findExpressBusinessViewStartLink() + "?uuid=" + iCase.uuid();
    } else {
      additionalCaseDetailsPageUri = getBusinessDetailURLFromCustomField(iCase);
    }
    return additionalCaseDetailsPageUri;
  }

  private String getBusinessDetailURLFromCustomField(ICase iCase) {
    // Flag to indicate if the current version is 8
    Boolean isVersion8 = false;

    // Attempt to fetch the custom field value from BUSINESS_DETAILS, if not found, try
    // CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE
    String customFieldValue = iCase.customFields()
        .stringField(com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS).getOrNull();
    if (StringUtils.isEmpty(customFieldValue)) {
      customFieldValue = iCase.customFields()
          .textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull();
      // If the link is from this custom field -> the link is from V8
      isVersion8 = true;
    }

    if (StringUtils.isNotEmpty(customFieldValue)) {
      if (customFieldValue.startsWith(IApplication.current().getName())) {
        // Custom field is IWebStartable id
        return iWebStartableCustomfield(iCase, customFieldValue);
        // Custom field is external link
      } else if (detectExternalLink(customFieldValue)) {
        return customFieldValue;
        // Custom field is link need to migrate from ver 8/10 link to IWebStartableId
      } else {
        return processPathUrlCustomField(iCase, customFieldValue, isVersion8);
      }
    } else {
      return constructDefaultURL(iCase);
    }
  }


  private String iWebStartableCustomfield(ICase iCase, String customFieldValue) {
    Boolean isEmbedInFrame =
        iCase.customFields().numberField(com.axonivy.portal.components.constant.CustomFields.EMBED_IN_FRAME).get()
            .orElse(1).intValue() == 1;
    IWebStartable iWebStartable = IWorkflowSession.current().findStartable(customFieldValue).orElse(null);
    if (iWebStartable != null) {
      return buildURL(iCase, iWebStartable.getLink().getRelative(), isEmbedInFrame);
    }
    return StringUtils.EMPTY;
  }

  private String processPathUrlCustomField(ICase iCase, String customFieldValue, Boolean isVerion8) {
    Boolean isEmbedInFrame = customFieldValue.contains("embedInFrame");
    String processUrl = removeQueryParameters(customFieldValue);

    // Find the IWebStartable object based on the process relative link and version
    IWebStartable iWebStartable = isVerion8 ? IWebStartableAPI.findIWebStartableByProcessRelativeLinkVer8(processUrl)
        : IWebStartableAPI.findIWebStartableByProcessRelativeLinkVer10(processUrl);
    updateCustomFields(iCase, iWebStartable, isEmbedInFrame);
    return buildURL(iCase, iWebStartable.getLink().getRelative(), isEmbedInFrame);
  }

  private void updateCustomFields(ICase iCase, IWebStartable iWebStartable, Boolean isEmbedInFrame) {
    iCase.customFields().stringField(com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS)
        .set(iWebStartable.getId());
    iCase.customFields().numberField(CustomFields.EMBED_IN_FRAME).set(isEmbedInFrame ? 1 : 0);
  }

  private String constructDefaultURL(ICase iCase) {
    Map<String, String> params = new HashMap<>();
    params.put("uuid", String.valueOf(iCase.uuid()));
    return PortalNavigator.buildUrlByKeyword("showAdditionalCaseDetails",
        START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE, params);
  }

  private String buildURL(ICase iCase, String relativeLink, boolean isEmbedInFrame) {
    return relativeLink + "?uuid=" + iCase.uuid() + (isEmbedInFrame ? "&embedInFrame" : "");
  }

  private static boolean detectExternalLink(String path) {
    return StringUtils.startsWithIgnoreCase(path, "http") || StringUtils.startsWithIgnoreCase(path, "https");
  }

  private String removeQueryParameters(String url) {
    int queryIndex = url.indexOf("?");
    if (queryIndex != -1) {
      return url.substring(0, queryIndex);
    } else {
      return url;
    }
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
    return PortalProcessViewerUtils.isShowProcessViewer(caze);
  }

}
