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

import com.axonivy.portal.util.BusinessDetailsUtils;

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

}
