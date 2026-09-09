package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.enums.GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK;
import static ch.ivy.addon.portalkit.enums.SessionAttribute.GROWL_MESSAGE_TYPE;
import static com.axonivy.portal.components.enums.SessionAttribute.CUSTOM_GROWL_MESSAGE;
import static com.axonivy.portal.components.enums.SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GrowlMessageType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class GrowlMessageService {
  public static final String PORTAL_GLOBAL_GROWL_MESSAGE = "portal-global-growl-message";
  public static final String PORTAL_GLOBAL_GROWL = "portal-global-growl";
  public static final String OVERRIDE_PORTAL_GROWL = "overridePortalGrowl";
  private static final String CASE_DETAILS = "/ch.ivy.addon.portalkit.ui.jsf/common/linkToCaseDetails";

  private static GrowlMessageService instance;

  private GrowlMessageService() {
  }

  public static GrowlMessageService getInstance() {
    if (instance == null) {
      synchronized (GrowlMessageService.class) {
        if (instance == null) {
          instance = new GrowlMessageService();
        }
      }
    }
    return instance;
  }

  /**
   * Adds the growl message shown once a task ended: the message a process set through
   * {@code PortalGrowlMessageAPI} when there is one, otherwise Portal's default feedback.
   */
  public void addTaskEndFeedbackMessage(Boolean isTaskFinished, ICase iCase) {
    FacesMessage customMessage = takeCustomMessage();
    boolean isProcessViewerClosed = takePendingGrowlMessageType() == GrowlMessageType.PROCESS_VIEWER;
    if (customMessage != null) {
      addFeedbackMessageIfEnabled(() -> customMessage);
    } else if (isProcessViewerClosed) {
      addFeedbackMessageProcessViewer();
    } else {
      addFeedbackMessage(isTaskFinished, iCase);
    }
  }

  public void addFeedbackMessage(Boolean isTaskFinished) {
    addFeedbackMessage(isTaskFinished, null);
  }

  public void addFeedbackMessage(Boolean isTaskFinished, ICase iCase) {
    addFeedbackMessageIfEnabled(() -> taskEndMessage(iCase, BooleanUtils.toBoolean(isTaskFinished)));
  }

  public void addFeedbackMessageProcessViewer() {
    if (!Ivy.session().isSessionUserUnknown()) {
      addGlobalGrowlMessage(() -> FacesMessageUtils.sanitizedMessage(GrowlMessageType.PROCESS_VIEWER.message(), ""));
    }
  }

  /**
   * Reads and clears the message a process set through {@code PortalGrowlMessageAPI}. It is always
   * cleared, even when growl feedback is turned off, so that it cannot resurface on a later task.
   */
  private FacesMessage takeCustomMessage() {
    Object summary = takeSessionAttribute(CUSTOM_GROWL_MESSAGE);
    Object detail = takeSessionAttribute(CUSTOM_GROWL_MESSAGE_DETAIL);
    return summary == null ? null
        : FacesMessageUtils.sanitizedMessage(summary.toString(), Objects.toString(detail, StringUtils.EMPTY));
  }

  private void addFeedbackMessageIfEnabled(Supplier<FacesMessage> message) {
    if (isFeedbackMessageEnabled() && !Ivy.session().isSessionUserUnknown()) {
      addGlobalGrowlMessage(message);
    }
  }

  private void addGlobalGrowlMessage(Supplier<FacesMessage> message) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    if (!flash.containsKey(OVERRIDE_PORTAL_GROWL)) {
      FacesContext.getCurrentInstance().addMessage(PORTAL_GLOBAL_GROWL_MESSAGE, message.get());
    }
    flash.setRedirect(true);
    flash.setKeepMessages(true);
  }

  private FacesMessage taskEndMessage(ICase iCase, boolean isTaskFinished) {
    String caseDetailsUrl =
        iCase != null ? PortalNavigator.buildPortalCaseDetailsUrl(iCase.getBusinessCase().uuid()) : null;
    if (!isCaseDetailsAvailable(iCase, caseDetailsUrl)) {
      return FacesMessageUtils.sanitizedMessage(taskEndText(isTaskFinished));
    }
    String summary = isTaskFinished ? taskEndText(true) + "." : taskEndText(false);
    return FacesMessageUtils.sanitizedMessage(summary, Ivy.cms().co(CASE_DETAILS, Arrays.asList(caseDetailsUrl)));
  }

  private String taskEndText(boolean isTaskFinished) {
    return isTaskFinished ? GrowlMessageType.TASK_FINISHED.message() : GrowlMessageType.TASK_LEFT.message();
  }

  private boolean isCaseDetailsAvailable(ICase iCase, String caseDetailsUrl) {
    return iCase != null && iCase.isPersistent() && StringUtils.isNotBlank(caseDetailsUrl);
  }

  private boolean isFeedbackMessageEnabled() {
    String variable = GlobalSettingService.getInstance().findGlobalSettingValue(DISPLAY_MESSAGE_AFTER_FINISH_TASK);
    return StringUtils.isNotBlank(variable) ? Boolean.parseBoolean(variable) : true;
  }

  private GrowlMessageType takePendingGrowlMessageType() {
    Object messageType = takeSessionAttribute(GROWL_MESSAGE_TYPE);
    return messageType == null ? null : EnumUtils.getEnum(GrowlMessageType.class, messageType.toString());
  }

  private Object takeSessionAttribute(Enum<?> attribute) {
    Object value = Ivy.session().getAttribute(attribute.name());
    if (value != null) {
      Ivy.session().removeAttribute(attribute.name());
    }
    return value;
  }
}
