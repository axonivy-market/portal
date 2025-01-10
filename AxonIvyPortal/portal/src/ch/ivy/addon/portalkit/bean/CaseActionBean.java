package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.enums.AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE;
import static ch.ivy.addon.portalkit.enums.PortalPermission.SHOW_CASE_DETAILS;
import static com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.bean.UserMenuBean;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PortalProcessViewerUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@RequestScoped
public class CaseActionBean implements Serializable {

  private static final long serialVersionUID = 7468665222036995531L;
  private static final String CASE_ID = "caseId";
  private static final String UUID = "uuid";
  private static final String START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE = "Start Processes/PortalStart/showAdditionalCaseDetails.ivp";
  private boolean isShowCaseDetails;

  @PostConstruct
  public void initCaseActions() {
    isShowCaseDetails = PermissionUtils.hasPortalPermission(SHOW_CASE_DETAILS);
  }

  public String getAdditionalCaseDetailsPageUri(ICase iCase) {
    return getBusinessDetailUrlFromCustomField(iCase);
  }


  /**
   * Attempt to fetch the custom field businessDetails, if not found, try CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.
   * <p/>
   * Version 8, custom text field name CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, value like
   * /ivy/pro/designer/PortalExamples/1624D1F5CBEA5332/showInvestmentRequestCustomFields.ivp?caseId=10
   * <p/>
   * Version 10.0.0 to 10.0.10, custom text field name CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, value like
   * /designer/pro/portal-components-examples/176465FBFE257CF3/showInvestmentRequestCustomFields.ivp?caseId=3
   * <p/>
   * Version 10.0.11 to 11.3.1, custom string field name businessDetails, value like
   * /designer/pro/portal-components-examples/1624D1F5CBEA5332/showInvestmentRequestCustomFields.ivp?caseId=3&embedInFrame
   * <p/>
   * Version after 11.3.x, custom string field name businessDetails, if migrated, value is IWebStartable ID + existing
   * queryString
   * <p/>
   * Version after 11.3.x, custom string field name businessDetails, if new cases, value is IWebStartable ID +
   * queryString. QueryString does not contain caseId, uuid. It could be embedInFrame or empty. The value is like
   * designer/portal-components-examples/Start
   * Processes/BusinessDetails/showInvestmentRequestCustomFields.ivp?embedInFrame
   * <p/>
   * How to detect relative path or IWebStartable ID to migrate to version after 11.3.x? If the value is an external
   * link or IWebStartable ID then keep as it is. If the value starts with `/` (not external link, not IWebStartable
   * ID), then migrate to IWebStartable ID but still keep queryString as it is.
   * <p/>
   * How to distinguish relative path of version 8 and 10? Extract the text between /pro/ and ?, then split by /, if it
   * has 4 parts => version 8, if it has 3 parts => version >= 10
   * <p/>
   * How to get business details URL from IWebStartable ID? Find IWebStartable, IWebStartable.getLink().getRelative(),
   * get queryString from custom field, append caseId, uuid if missing
   * If migrated from custom text field, add embedInFrame in query string.
   **/
  private String getBusinessDetailUrlFromCustomField(ICase iCase) {
    String customFieldValue = iCase.customFields().stringField(BUSINESS_DETAILS).getOrNull();
    if (StringUtils.isEmpty(customFieldValue)) {
      customFieldValue = iCase.customFields().textField(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.name()).getOrNull();
      if (StringUtils.isEmpty(customFieldValue)) {
        return constructDefaultBusinessDetailsUrl(iCase);
      } else {
        customFieldValue += "&embedInFrame";
      }
    }
    if (isExternalLink(customFieldValue)) {
      return customFieldValue;
    }
    if (customFieldValue.startsWith("/")) {
      migrateCustomFieldForBusinessDetailsPage(iCase, customFieldValue);
    }
    return getBusinessDetailsUrl(iCase);
  }

  private String getBusinessDetailsUrl(ICase iCase) {
    String customFieldValue = iCase.customFields().stringField(BUSINESS_DETAILS).getOrDefault(EMPTY);
    String parts[] = customFieldValue.split("\\?", 2);
    String iWebStartableId = parts[0];
    IWebStartable iWebStartable = IWorkflowSession.current().findStartable(iWebStartableId).orElse(null);
    if (iWebStartable == null) {
      return EMPTY;
    }
    String configuredQueryString = parts.length == 2 ? parts[1] : EMPTY;
    Map<String, String> keyToValue = new HashMap<>();
    if (!configuredQueryString.contains(CASE_ID)) {
      keyToValue.put(CASE_ID, String.valueOf(iCase.getId()));
    }
    if (!configuredQueryString.contains(UUID)) {
      keyToValue.put(UUID, String.valueOf(iCase.uuid()));
    }
    String url = iWebStartable.getLink().getRelative() + "?" + configuredQueryString + "&"
        + UrlUtils.buildUrlQueryString(keyToValue);
    return url.replace("?&", "?");
  }

  private void migrateCustomFieldForBusinessDetailsPage(ICase iCase, String customFieldValue) {
    String relativePathInLatestFormat = createRelativeLinkInLatestFormat(customFieldValue);
    List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();
    IWebStartable iWebStartable = findTargetStartable(iWebStartables, relativePathInLatestFormat);
    if (iWebStartable == null) {
      throw new PortalException(
          String.format("Cannot find IWebStartable by process path [%s].", relativePathInLatestFormat));
    }
    String[] parts = customFieldValue.split("\\?", 2);
    String queryString = parts.length > 1 ? parts[1] : EMPTY;

    updateCustomFieldBusinessDetails(iCase, iWebStartable, queryString);
  }

  private String createRelativeLinkInLatestFormat(String customFieldValue) {
    String[] textSplitedByPro = customFieldValue.split("/pro/");
    String textAfterPro = textSplitedByPro[1];
    String[] textAfterProThenSplitedByQuestionMark = textAfterPro.split("\\?", 2);
    String[] textAfterProBeforeQuestionMarkSplitedBySlash = textAfterProThenSplitedByQuestionMark[0].split("/");
    String appName;
    String relativePathWithoutAppName;
    if (textAfterProBeforeQuestionMarkSplitedBySlash.length == 4) { // version 8
      appName = textAfterProBeforeQuestionMarkSplitedBySlash[0];
      relativePathWithoutAppName = String.join("/",
          Arrays.copyOfRange(textAfterProBeforeQuestionMarkSplitedBySlash, 1, textAfterProBeforeQuestionMarkSplitedBySlash.length));
    } else { // version 10
      String[] textBeforeProThenSplitedBySlash = textSplitedByPro[0].split("/");
      appName = textBeforeProThenSplitedBySlash[textBeforeProThenSplitedBySlash.length - 1];
      relativePathWithoutAppName = textAfterProThenSplitedByQuestionMark[0];
    }
    return String.format("%s/pro/%s", appName, relativePathWithoutAppName);
  }

  private static IWebStartable findTargetStartable(List<IWebStartable> iWebStartables, String targetPath) {
    for (IWebStartable startable : iWebStartables) {
      String startableRelativeLink = startable.getLink().getRelative();
      if (startableRelativeLink.endsWith(targetPath)) {
        return startable;
      }
    }
    return null;
  }
  private void updateCustomFieldBusinessDetails(ICase iCase, IWebStartable iWebStartable, String params) {
    ICustomStringField customField = iCase.customFields().stringField(BUSINESS_DETAILS);
    String newValue = iWebStartable.getId() + (StringUtils.isNotEmpty(params) ? "?" + params : EMPTY);
    Ivy.log().warn("Updated case custom field businessDetails from {0} to {1}", customField.getOrNull(), newValue);
    customField.set(newValue);
  }

  private String constructDefaultBusinessDetailsUrl(ICase iCase) {
    Map<String, String> params = new HashMap<>();
    params.put(UUID, String.valueOf(iCase.uuid()));
    return PortalNavigator.buildUrlByKeyword("showAdditionalCaseDetails",
        START_PROCESSES_SHOW_ADDITIONAL_CASE_DETAILS_PAGE, params);
  }

  private static boolean isExternalLink(String path) {
    return StringUtils.startsWithIgnoreCase(path, "http:") || StringUtils.startsWithIgnoreCase(path, "https:");
  }

  public String getProcessViewerPageUri(ICase selectedCase) {
    Ivy.log().warn("===== getProcessViewerPageUri");
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

}
