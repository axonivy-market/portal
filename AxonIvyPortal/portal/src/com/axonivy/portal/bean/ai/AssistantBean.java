package com.axonivy.portal.bean.ai;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.enums.PortalVariable;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.ai.Assistant;
import ch.ivy.addon.portalkit.service.AssistantService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class AssistantBean implements Serializable {

  private static final long serialVersionUID = 1683098437048122830L;

  private Assistant assistant;
  private String assistantEndPoint;
  private String assistantJson;

  public void initBean() {
    this.assistantEndPoint = getEndpoint();
    this.assistant = AssistantService.getInstance().getPublicConfig().get(0);
    this.assistant.initToolkit();
    this.assistantJson = this.assistant.buildDetailsJson();

  }

  public String getEndpoint() {
    return Ivy.var().get(PortalVariable.CHATBOT_ENDPOINT.key)
        .concat("/assistant/");
  }

  public String generateLinkToTaskDetails(ITask workingTask) {
    if (workingTask == null) {
      return PortalNavigator.getDashboardLink();
    }

    return PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID(workingTask.uuid());
  }

  public String generateLinkToCaseDetails(ICase workingCase) {
    if (workingCase == null) {
      return PortalNavigator.getDashboardLink();
    }
    return PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID(workingCase.uuid());
  }

  public Assistant getAssistant() {
    return assistant;
  }

  public void setAssistant(Assistant assistant) {
    this.assistant = assistant;
  }

  public String getAssistantEndPoint() {
    return assistantEndPoint;
  }

  public void setAssistantEndPoint(String assistantEndPoint) {
    this.assistantEndPoint = assistantEndPoint;
  }

  public String getAssistantJson() {
    return assistantJson;
  }

  public void setAssistantJson(String assistantJson) {
    this.assistantJson = assistantJson;
  }
}
