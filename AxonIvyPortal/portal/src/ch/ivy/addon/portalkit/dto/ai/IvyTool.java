package ch.ivy.addon.portalkit.dto.ai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.enums.ai.RunState;
import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.enums.ai.ToolType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import ch.ivyteam.ivy.environment.Ivy;

public class IvyTool extends AiTool {

  private static final long serialVersionUID = -5362479525475837795L;

  private List<IvyToolAttribute> attributes;
  private String signature;
  private List<String> requirements;
  private String postAction;

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
    return ToolType.IVY;
  }

  @JsonIgnore
  public String getResult() {
    if (!hasPermision()) {
      setSteps(Arrays.asList(createNoPermisisonStep()));
      setWorkingStepNo(0);
      return BusinessEntityConverter
          .entityToJsonValue(getSteps().get(getWorkingStepNo()));
    }

    Map<String, Object> params = new HashMap<>();
    getAttributes().forEach(attr -> {
      params.put(attr.getName(), attr.getValue());
    });
    Map<String, Object> result = IvyAdapterService
        .startSubProcessInProjectAndAllRequired(getSignature(), params);

    if (CollectionUtils.isEmpty(getSteps())) {
      IvyToolStep step = new IvyToolStep();
      step.setStepNo(0);
      setWorkingStepNo(0);
      step.setToolName(getName());
      step.setPostAction(getPostAction());

      if (result != null && !result.isEmpty()) {
        step.setResult((String) result.get("result"));
        step.setDescription(getDescription());
        step.setState(RunState.DONE);
      } else {
        step.setResult(
            "Error happened when proceed your request, please try again.");
        step.setState(RunState.ERROR);
      }

      setSteps(Arrays.asList(step));
    }
    return BusinessEntityConverter
        .entityToJsonValue(getSteps().get(getWorkingStepNo()));
  }

  @Override
  public JsonNode buildJsonNode() {
    return BusinessEntityConverter.entityToJsonNode(this);
  }

  public String getPostAction() {
    return postAction;
  }

  public void setPostAction(String postAction) {
    this.postAction = postAction;
  }

  private boolean hasPermision() {
    boolean hasPermission = false;
    for (String permission : getPermissions()) {

      // Check if username of login user equals to the permission.
      if (permission.startsWith("#")) {
        hasPermission = Ivy.session().getSessionUserName()
            .contentEquals(permission.substring(1));
      } else {
        // Check if the permission is existing in the role list of login user.
        hasPermission = Ivy.session().getSessionUser().getAllRoles().stream()
            .anyMatch(role -> role.getName().contentEquals(permission));
      }

      if (hasPermission) {
        break;
      }
    }
    return hasPermission;
  }

  private IvyToolStep createNoPermisisonStep() {
    IvyToolStep step = new IvyToolStep();
    step.setStepNo(0);
    step.setResult("Sorry, you don't have permission to proceed this request.");
    step.setState(RunState.ERROR);
    step.setToolName(getName());
    return step;
  }
}
