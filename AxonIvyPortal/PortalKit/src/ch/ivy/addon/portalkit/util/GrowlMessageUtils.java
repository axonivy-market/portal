package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.enums.GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK;
import static ch.ivy.addon.portalkit.util.ProcessUtils.getURLPortalCaseDetails;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public final class GrowlMessageUtils {
  private static final String TASK_LEFT_WITH_DETAILS =
      "/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfullyWithDetails";
  private static final String TASK_FINISHED_WITH_DETAILS =
      "/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfullyWithDetails";
  private static final String TASK_LEFT = "/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully";
  private static final String TASK_FINISHED = "/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully";

  private GrowlMessageUtils() {}

  public static void addFeedbackMessage(Boolean isTaskFinishedParam) {
    addFeedbackMessage(isTaskFinishedParam, null);
  }

  public static void addFeedbackMessage(Boolean isTaskFinished, ICase iCase) {
    if (isMessageDisplayedAfterFinishTaskEnable() && !Ivy.session().isSessionUserUnknown()) {
      Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
      if (!flash.containsKey("overridePortalGrowl")) {
        addMessageToFacesContext(iCase, BooleanUtils.toBoolean(isTaskFinished));
      }
      flash.setRedirect(true);
      flash.setKeepMessages(true);
    }
  }

  private static void addMessageToFacesContext(ICase iCase, boolean isTaskFinished) {
    String caseDetailsUrl = iCase != null ? getURLPortalCaseDetails(iCase.getBusinessCase().getId()) : null;
    FacesMessage message = null;
    if (isCaseDetailsAvailable(iCase, caseDetailsUrl)) {
      message = addMessageWithoutCaseDetails(isTaskFinished);
    } else {
      message = addMessageWithCaseDetails(isTaskFinished, caseDetailsUrl);
    }
    FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
  }

  private static FacesMessage addMessageWithoutCaseDetails(boolean isTaskFinished) {
    return new FacesMessage(isTaskFinished ? Ivy.cms().co(TASK_FINISHED) : Ivy.cms().co(TASK_LEFT));
  }

  private static FacesMessage addMessageWithCaseDetails(boolean isTaskFinished, String caseDetailsUrl) {
    List<Object> caseDetailsParam = Arrays.asList(caseDetailsUrl);
    return new FacesMessage(isTaskFinished ? Ivy.cms().co(TASK_FINISHED_WITH_DETAILS, caseDetailsParam)
        : Ivy.cms().co(TASK_LEFT_WITH_DETAILS, caseDetailsParam));
  }

  private static boolean isCaseDetailsAvailable(ICase iCase, String caseDetailsUrl) {
    return iCase == null || !iCase.isPersistent() || StringUtils.isBlank(caseDetailsUrl);
  }

  private static boolean isMessageDisplayedAfterFinishTaskEnable() {
    String variable = new GlobalSettingService().findGlobalSettingValue(DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
    return StringUtils.isNotBlank(variable) ? Boolean.parseBoolean(variable) : true;
  }
}

