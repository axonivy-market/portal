package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.enums.GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.components.util.FacesMessageUtils;
import com.axonivy.portal.enums.PortalCustomSignature;

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

  private Map<String, String> mapMessages = new HashMap<>();

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
    instance.setCustomGrowlMessages();
    return instance;
  }

  public void addFeedbackMessage(Boolean isTaskFinishedParam) {
    addFeedbackMessage(isTaskFinishedParam, null);
  }

  public void addFeedbackMessage(Boolean isTaskFinished, ICase iCase) {
    if (isMessageDisplayedAfterFinishTaskEnable() && !Ivy.session().isSessionUserUnknown()) {
      Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
      if (!flash.containsKey(OVERRIDE_PORTAL_GROWL)) {
        addMessageToFacesContext(iCase, BooleanUtils.toBoolean(isTaskFinished));
      }
      flash.setRedirect(true);
      flash.setKeepMessages(true);
    }
  }

  public void addFeedbackMessageProcessViewer() {
    if (!Ivy.session().isSessionUserUnknown()) {
      Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
      if (!flash.containsKey(OVERRIDE_PORTAL_GROWL)) {
        FacesMessage message = FacesMessageUtils.sanitizedMessage(getMessageByType(GrowlMessageType.PROCESS_VIEWER),
            "");
        FacesContext.getCurrentInstance().addMessage(PORTAL_GLOBAL_GROWL_MESSAGE, message);
      }
      flash.setRedirect(true);
      flash.setKeepMessages(true);
    }
  }
  
  @SuppressWarnings("unchecked")
  private void setCustomGrowlMessages() {
    Map<String, Object> result = IvyAdapterService
        .startSubProcessInSecurityContext(PortalCustomSignature.GROWL_MESSAGE.getSignature(), null);
    if (!result.isEmpty()) {
      Map<String, String> mapMessage = (Map<String, String>) result.get("mapMessages");
      if (!mapMessage.isEmpty()) {
        mapMessages.putAll(mapMessage);
      }
    }

  }

  private void addMessageToFacesContext(ICase iCase, boolean isTaskFinished) {
    String caseDetailsUrl = iCase != null ? PortalNavigator.buildPortalCaseDetailsUrl(iCase.getBusinessCase().getId())
        : null;
    FacesMessage message = null;
    if (isCaseDetailsAvailable(iCase, caseDetailsUrl)) {
      message = addMessageWithoutCaseDetails(isTaskFinished);
    } else {
      message = addMessageWithCaseDetails(isTaskFinished, caseDetailsUrl);
    }
    FacesContext.getCurrentInstance().addMessage(PORTAL_GLOBAL_GROWL_MESSAGE, message);
  }

  private String getMessageByType(GrowlMessageType messageType) {
    return this.mapMessages.getOrDefault(messageType.name(), messageType.message());
  }

  private FacesMessage addMessageWithoutCaseDetails(boolean isTaskFinished) {
    return FacesMessageUtils.sanitizedMessage(isTaskFinished ? getMessageByType(GrowlMessageType.TASK_FINISHED)
        : getMessageByType(GrowlMessageType.TASK_LEFT));
  }

  private FacesMessage addMessageWithCaseDetails(boolean isTaskFinished, String caseDetailsUrl) {
    String message = isTaskFinished ? getMessageByType(GrowlMessageType.TASK_FINISHED) + "."
        : getMessageByType(GrowlMessageType.TASK_LEFT);
    String description = Ivy.cms().co(CASE_DETAILS, Arrays.asList(caseDetailsUrl));
    return FacesMessageUtils.sanitizedMessage(message, description);
  }

  private boolean isCaseDetailsAvailable(ICase iCase, String caseDetailsUrl) {
    return iCase == null || !iCase.isPersistent() || StringUtils.isBlank(caseDetailsUrl);
  }

  private boolean isMessageDisplayedAfterFinishTaskEnable() {
    String variable = GlobalSettingService.getInstance().findGlobalSettingValue(DISPLAY_MESSAGE_AFTER_FINISH_TASK);
    return StringUtils.isNotBlank(variable) ? Boolean.parseBoolean(variable) : true;
  }
}
