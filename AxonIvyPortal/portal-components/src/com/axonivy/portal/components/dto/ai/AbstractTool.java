package com.axonivy.portal.components.dto.ai;

import java.io.Serializable;
import java.util.List;

import com.axonivy.portal.components.enums.ToolType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @Type(value = IvyTool.class, name = "IVY"),
  @Type(value = IvyCallableTool.class, name = "IVY_CALLABLE"),
  @Type(value = RetrievalQATool.class, name = "RETRIEVAL_QA")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractTool implements Serializable {

  private static final long serialVersionUID = -4652001849527628818L;

  private String name;
  private String description;
  private List<String> permissions;
  private boolean isDefault;
  private List<IvyToolStep> steps;
  private int workingStepNo;

  public AbstractTool() {}

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getPermissions() {
    return permissions;
  }
  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public abstract void init();
  public boolean isDefault() {
    return isDefault;
  }
  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

  public abstract ToolType getType();

  public List<IvyToolStep> getSteps() {
    return steps;
  }

  public void setSteps(List<IvyToolStep> steps) {
    this.steps = steps;
  }

  public int getWorkingStepNo() {
    return workingStepNo;
  }

  public void setWorkingStepNo(int workingStepNo) {
    this.workingStepNo = workingStepNo;
  }
}
