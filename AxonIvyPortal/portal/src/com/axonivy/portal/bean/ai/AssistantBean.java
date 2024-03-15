package com.axonivy.portal.bean.ai;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.PortalVariable;

import ch.ivy.addon.portalkit.dto.ai.AiTool;
import ch.ivy.addon.portalkit.dto.ai.Assistant;
import ch.ivy.addon.portalkit.service.AiToolService;
import ch.ivy.addon.portalkit.service.AssistantService;
import ch.ivyteam.ivy.environment.Ivy;

public abstract class AssistantBean implements Serializable {

  private static final long serialVersionUID = 1683098437048122830L;

  private Assistant assistant;
  private String assistantEndPoint;
  private String assistantJson;
  private List<AiTool> availableTools;

  public void initBean() {
    this.assistantEndPoint = getEndpoint();
    this.assistant = AssistantService.getInstance().getPublicConfig().get(0);
    this.assistant.initToolkit();
    this.assistantJson = StringEscapeUtils
        .escapeJava(this.assistant.buildDetailsJson());

    this.setAvailableTools(CollectionUtils
        .disjunction(AiToolService.getInstance().getPublicConfig(),
            this.assistant.getToolkit())
        .stream().collect(Collectors.toList()));

  }

  public String getEndpoint() {
    return Ivy.var().get(PortalVariable.CHATBOT_ENDPOINT.key)
        .concat("/assistant/");
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

  public void addTool(String toolId) {
    if (StringUtils.isBlank(toolId)) {
      return;
    }

    AiTool tool = getAvailableTools().stream()
        .filter(t -> t.getId().contentEquals(toolId)).findFirst().get();
    this.assistant.addTool(tool);
    getAvailableTools().remove(tool);
  }

  public void removeTool(String toolId) {
    if (StringUtils.isBlank(toolId)) {
      return;
    }
    AiTool tool = this.assistant.getToolkit().stream()
        .filter(t -> t.getId().contentEquals(toolId)).findFirst().get();
    this.assistant.removeTool(tool);
    getAvailableTools().add(tool);
  }

  public abstract void save();

  public List<AiTool> getAvailableTools() {
    return availableTools;
  }

  public void setAvailableTools(List<AiTool> availableTools) {
    this.availableTools = availableTools;
  }

  public abstract void onCancel();
}
