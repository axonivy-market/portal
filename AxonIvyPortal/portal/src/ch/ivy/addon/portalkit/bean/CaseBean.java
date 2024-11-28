package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.DateTimeFormatterUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.TimesUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;

@ManagedBean(name = "caseBean")
@ViewScoped
public class CaseBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String OPEN_CASES_LIST = "Start Processes/PortalStart/CaseListPage.ivp";
  private boolean isHideCaseCreator;
  private boolean isCaseOwnerEnabled;

  @PostConstruct
  public void init() {
    isCaseOwnerEnabled = GlobalSettingService.getInstance().isCaseOwnerEnabled();
    isHideCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
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
    return getDisplayState(iCase.getBusinessState());
  }

  public String getDisplayState(CaseBusinessState caseState) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/businessCaseState/" + caseState);
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

  public String getAriaLabel(ICase icase, List<CaseColumnModel> columns) {
    List<String> displayTexts = new ArrayList<>();
    for (CaseColumnModel col : columns) {
      if (col.getVisible()) {
        if (DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(col.getField())) {
          displayTexts.add(col.getHeaderText() + ": " + getState(icase));
        } else if (DashboardStandardCaseColumn.CREATED.getField().equalsIgnoreCase(col.getField())) {
          String createdDateString = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(icase.getStartTimestamp());
          displayTexts.add(col.getHeaderText() + ": " + createdDateString);
        } else if (DashboardStandardCaseColumn.FINISHED.getField().equalsIgnoreCase(col.getField())) {
          if (icase.getEndTimestamp() != null) {
            String finishDateString = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern()).format(icase.getEndTimestamp());
            displayTexts.add(col.getHeaderText() + ": " + finishDateString);
          }
        } else {
          Object displayObject = col.display(icase);
          if (displayObject != null && StringUtils.isNotEmpty(displayObject.toString())) {
            displayTexts.add(col.getHeaderText() + ": " + displayObject.toString());
          }
        }
      }
    }
    return String.join(" - ", displayTexts);
  }

}
