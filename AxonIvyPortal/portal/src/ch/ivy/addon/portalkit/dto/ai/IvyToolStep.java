package ch.ivy.addon.portalkit.dto.ai;

import com.axonivy.portal.components.enums.ai.RunState;
import com.axonivy.portal.enums.ai.StepType;

public class IvyToolStep {
  private int stepNo;
  private StepType type;
  private String description;
  private String result;
  private RunState state;
  private String toolId;
  private String postAction;

  public int getStepNo() {
    return stepNo;
  }
  public void setStepNo(int stepNo) {
    this.stepNo = stepNo;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public RunState getState() {
    return state;
  }
  public void setState(RunState state) {
    this.state = state;
  }

  public String getToolId() {
    return toolId;
  }

  public void setToolId(String toolId) {
    this.toolId = toolId;
  }

  public String getPostAction() {
    return postAction;
  }

  public void setPostAction(String postAction) {
    this.postAction = postAction;
  }

  public StepType getType() {
    return type;
  }
  public void setType(StepType type) {
    this.type = type;
  }
}
