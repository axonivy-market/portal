package com.axonivy.portal.bean.ai;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.enums.ai.ToolType;

import ch.ivy.addon.portalkit.dto.ai.AiTool;
import ch.ivy.addon.portalkit.dto.ai.Assistant;
import ch.ivy.addon.portalkit.dto.ai.IvyTool;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class AssistantManagementBean {

  private ToolType[] toolTypes = ToolType.values();

  private List<Assistant> assistants;
  private List<IvyTool> ivyTools;

  private Assistant selectedAssistant;
  private AiTool selectedTool;
  private boolean isAddNewTool;

  public void init() {
    setAssistants(BusinessEntityConverter.jsonValueToEntities(Ivy.var().get("Assistants"), Assistant.class));
    setIvyTools(BusinessEntityConverter.jsonValueToEntities(Ivy.var().get("IvyTools"), IvyTool.class));
    this.isAddNewTool = false;
  }

  public List<Assistant> getAssistants() {
    return assistants;
  }

  public void setAssistants(List<Assistant> assistants) {
    this.assistants = assistants;
  }

  public Assistant getSelectedAssistant() {
    return selectedAssistant;
  }

  public void setSelectedAssistant(Assistant selectedAssistant) {
    this.selectedAssistant = selectedAssistant;
  }

  public AiTool getSelectedTool() {
    return selectedTool;
  }

  public void setSelectedTool(AiTool selectedTool) {
    this.selectedTool = selectedTool;
  }

  public boolean isAddNewTool() {
    return isAddNewTool;
  }

  public void addTool() {
    this.isAddNewTool = true;
  }

  public void finishAddTool() {
    this.isAddNewTool = false;
  }

  public ToolType[] getToolTypes() {
    return toolTypes;
  }

  public void setToolTypes(ToolType[] toolTypes) {
    this.toolTypes = toolTypes;
  }

  public void onAddNewAssistant() {
    this.selectedAssistant = new Assistant();
    this.selectedAssistant.setToolkit(new ArrayList<>());
  }

  public List<IvyTool> getIvyTools() {
    return ivyTools;
  }

  public void setIvyTools(List<IvyTool> ivyTools) {
    this.ivyTools = ivyTools;
  }
}
