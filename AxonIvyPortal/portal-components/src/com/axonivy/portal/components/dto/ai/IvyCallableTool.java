package com.axonivy.portal.components.dto.ai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.enums.ToolType;
import com.axonivy.portal.components.enums.ai.RunState;
import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class IvyCallableTool extends AbstractTool {

  private static final long serialVersionUID = -5362479525475837795L;

  private List<IvyToolAttribute> attributes;
  private String signature;
  private List<String> requirements;

  public List<IvyToolAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<IvyToolAttribute> attributes) {
    this.attributes = attributes;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public List<String> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<String> requirements) {
    this.requirements = requirements;
  }

  @Override
  public void init() {
  }

  @Override
  public ToolType getType() {
    return ToolType.IVY_CALLABLE;
  }

  @JsonIgnore
  public String getResult() {
    Map<String, Object> params = new HashMap<>();
    getAttributes().forEach(attr -> {
      params.put(attr.getName(), attr.getValue());
    });
    Map<String, Object> result = IvyAdapterService.startSubProcessInProjectAndAllRequired(getSignature(), params);

    if (CollectionUtils.isEmpty(getSteps())) {
      IvyToolStep step = new IvyToolStep();
      step.setStepNo(0);
      setWorkingStepNo(0);
      step.setToolName(getName());

      if (result != null && !result.isEmpty()) {
        step.setResult((String) result.get("result"));
        step.setDescription(getDescription());
        step.setState(RunState.DONE);
      } else {
        step.setResult("Error happened when proceed your request, please try again.");
        step.setState(RunState.ERROR);
      }

      setSteps(Arrays.asList(step));
    }
    return BusinessEntityConverter.entityToJsonValue(getSteps().get(getWorkingStepNo()));
  }
}
