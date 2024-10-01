package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.DateTimeFormatterUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.TimesUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean(name = "caseBean")
@ViewScoped
public class CaseBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String STATE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/caseState/";
  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";
  private boolean isHideCaseCreator;
  private boolean isCaseOwnerEnabled;

  @PostConstruct
  public void init() {
    isCaseOwnerEnabled = GlobalSettingService.getInstance().isCaseOwnerEnabled();
    isHideCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
  }

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
        return "si si-hourglass case-state-in-progress";
      case DONE:
        return "si si-check-circle-1 case-state-done";
      case DESTROYED:
      case ZOMBIE:
        return "si si-alert-circle case-state-zombie-destroyed";
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

  public String getDisplayState(CaseState caseState) {
    if (caseState == CaseState.CREATED) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/OPEN");
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + caseState);
  }
  
  public String getTranslatedState(CaseState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return Ivy.cms().co(STATE_CMS_PATH + state);
  }

  public void backToCasesList() {
    String friendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword("CaseListPage.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = OPEN_CASES_LIST;
    }
    String requestPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      TaskUtils.updateTaskStartedAttribute(false);
      PortalNavigator.redirect(requestPath);
    }
  }

  public String getDurationOfCase(ICase iCase) {
    return iCase.getEndTimestamp() != null ? getElapsedTimeForDoneCase(iCase) : getElapsedTimeForRunningCase(iCase);
  }

  private String getElapsedTimeForDoneCase(ICase iCase) {
    long duration = TimesUtils.getDurationInSeconds(iCase.getStartTimestamp(), iCase.getEndTimestamp());
    return DateTimeFormatterUtils.formatToShortTimeString(duration);
  }

  private String getElapsedTimeForRunningCase(ICase iCase) {
    long duration = TimesUtils.getDurationInSeconds(iCase.getStartTimestamp(), new Date());
    return DateTimeFormatterUtils.formatToShortTimeString(duration);
  }

  public String getDurationOfCaseOnTooltip(ICase iCase) {
    return iCase.getEndTimestamp() != null ? getElapsedTimeForDoneCaseOnTooltip(iCase)
        : getElapsedTimeForRunningCaseOnTooltip(iCase);
  }

  private String getElapsedTimeForDoneCaseOnTooltip(ICase iCase) {
    long duration = TimesUtils.getDurationInSeconds(iCase.getStartTimestamp(), iCase.getEndTimestamp());
    return DateTimeFormatterUtils.formatToExactTime(duration);
  }

  private String getElapsedTimeForRunningCaseOnTooltip(ICase iCase) {
    long duration = TimesUtils.getDurationInSeconds(iCase.getStartTimestamp(), new Date());
    return DateTimeFormatterUtils.formatToExactTime(duration);
  }

  public boolean isHideCaseCreator() {
    return isHideCaseCreator;
  }

  public void setHideCaseCreator(boolean isHideCaseCreator) {
    this.isHideCaseCreator = isHideCaseCreator;
  }

  public boolean isCaseOwnerEnabled() {
    return isCaseOwnerEnabled;
  }

  public void setCaseOwnerEnabled(boolean isCaseOwnerEnabled) {
    this.isCaseOwnerEnabled = isCaseOwnerEnabled;
  }
  
  public String getAriaLabel(ICase icase) {
    String ariaLabel = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/caseName") + ": " + icase.getName();
    ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/state") + ": " + getDisplayState(icase.getState());

    if (icase.getStartTimestamp() != null) {
      String createdDateString = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(icase.getStartTimestamp());
      ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATION_TIME") + ": " + createdDateString;
    }

    if (icase.getEndTimestamp() != null) {
      String finishedDateString = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(icase.getEndTimestamp());
      ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/FINISHED_TIME") + ": " + finishedDateString;
    }

    if (icase.getOwner() != null) {
      ariaLabel += " - " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/OWNER") + ": " + icase.getOwner().getDisplayName();
    }

    return ariaLabel;
  }

}
