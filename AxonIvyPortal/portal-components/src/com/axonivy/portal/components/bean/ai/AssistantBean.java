package com.axonivy.portal.components.bean.ai;

import com.axonivy.portal.components.dto.ai.Assistant;
import com.axonivy.portal.components.enums.PortalVariable;

import ch.ivyteam.ivy.environment.Ivy;

public abstract class AssistantBean {
  private Assistant assistant;

  public String getEndpoint() {
    return Ivy.var().get(PortalVariable.CHATBOT_ENDPOINT.key).concat("/assistant/");
  }

  public Assistant getAssistant() {
    return assistant;
  }

  public void setAssistant(Assistant assistant) {
    this.assistant = assistant;
  }
}
