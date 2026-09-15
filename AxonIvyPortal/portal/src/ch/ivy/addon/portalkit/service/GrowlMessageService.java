package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.enums.GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK;
import static com.axonivy.portal.components.enums.SessionAttribute.CUSTOM_GROWL_MESSAGE;
import static com.axonivy.portal.components.enums.SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.SessionAttribute;
import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.GrowlMessageType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class GrowlMessageService {
  public static final String PORTAL_GLOBAL_GROWL_MESSAGE = "portal-global-growl-message";
  public static final String PORTAL_GLOBAL_GROWL = "portal-global-growl";
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

  public void addTaskEndFeedbackMessage(Boolean isTaskFinished, ICase iCase) {
    FacesMessage customMessage = takeCustomMessage();
    boolean finished = BooleanUtils.toBoolean(isTaskFinished);
    addFeedbackMessageIfEnabled(() -> customMessage != null ? customMessage : taskEndMessage(iCase, finished));
  }

  public void addTaskLeftFeedbackMessage() {
    addTaskLeftFeedbackMessage(null);
  }

  public void addTaskLeftFeedbackMessage(ICase iCase) {
    addFeedbackMessageIfEnabled(() -> taskEndMessage(iCase, false));
  }

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
    FacesContext.getCurrentInstance().addMessage(PORTAL_GLOBAL_GROWL_MESSAGE, message.get());
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.setRedirect(true);
    flash.setKeepMessages(true);
  }

  private FacesMessage taskEndMessage(ICase iCase, boolean isTaskFinished) {
    String summary = taskEndText(isTaskFinished);
    String caseDetailsUrl = caseDetailsUrl(iCase);
    if (caseDetailsUrl == null) {
      return FacesMessageUtils.sanitizedMessage(summary);
    }
    return FacesMessageUtils.sanitizedMessage(isTaskFinished ? summary + "." : summary,
        Ivy.cms().co(CASE_DETAILS, Arrays.asList(caseDetailsUrl)));
  }

  private String taskEndText(boolean isTaskFinished) {
    return isTaskFinished ? GrowlMessageType.TASK_FINISHED.message() : GrowlMessageType.TASK_LEFT.message();
  }

  private String caseDetailsUrl(ICase iCase) {
    if (iCase == null || !iCase.isPersistent()) {
      return null;
    }
    String caseDetailsUrl = PortalNavigator.buildPortalCaseDetailsUrl(iCase.getBusinessCase().uuid());
    return StringUtils.isBlank(caseDetailsUrl) ? null : caseDetailsUrl;
  }

  private boolean isFeedbackMessageEnabled() {
    String variable = GlobalSettingService.getInstance().findGlobalSettingValue(DISPLAY_MESSAGE_AFTER_FINISH_TASK);
    return StringUtils.isNotBlank(variable) ? Boolean.parseBoolean(variable) : true;
  }

  private Object takeSessionAttribute(SessionAttribute attribute) {
    Object value = Ivy.session().getAttribute(attribute.name());
    if (value != null) {
      Ivy.session().removeAttribute(attribute.name());
    }
    return value;
  }
}
