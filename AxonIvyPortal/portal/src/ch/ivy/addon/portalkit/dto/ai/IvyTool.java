package ch.ivy.addon.portalkit.dto.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.ai.RunState;
import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.enums.ai.ToolType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.start.StartParameter;

public class IvyTool extends AiTool {

  private static final long serialVersionUID = 7282285288129523071L;

  private List<IvyToolAttribute> attributes;
  private String processPath;
  private List<String> requirements;

  @JsonIgnore
  private IWebStartable startableProcessStart;

  public IvyTool() {
  }

  @Override
  public void init() {
    initWebStartable();
    initAttributes();

    setName(getStartableProcessStart().getName());
    setDescription(getStartableProcessStart().getDescription());
    setPermissions(Arrays
        .asList(getStartableProcessStart().getActivator().getMemberName()));
  }

  public List<IvyToolAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<IvyToolAttribute> attributes) {
    this.attributes = attributes;
  }

  public List<String> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<String> requirements) {
    this.requirements = requirements;
  }

  private void initAttributes() {
    setAttributes(new ArrayList<>());
    List<StartParameter> params = loadParametersOfProcess();
    for (StartParameter param : params) {
      this.getAttributes()
          .add(new IvyToolAttribute(param.name(), "", param.description()));
    }

  }

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  @JsonIgnore
  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  @JsonIgnore
  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }

  private List<StartParameter> loadParametersOfProcess() {
    return Optional.ofNullable(getStartableProcessStart())
        .map(IWebStartable::parameters).orElse(new ArrayList<>());
  }

  private void initWebStartable() {
    ProcessService.getInstance().findProcesses().forEach(process -> {
      if (process.getId().contentEquals(this.getProcessPath())) {
        this.setStartableProcessStart(process);
        return;
      }
    });
  }

  @JsonIgnore
  public String getResult() {
    if (startableProcessStart == null) {
      initWebStartable();
    }

    Map<String, String> params = new HashMap<>();
    getAttributes().forEach(attr -> {
      if (StringUtils.isNotBlank(attr.getValue())) {
        params.put(attr.getName(), attr.getValue());
      }
    });

    if (CollectionUtils.isEmpty(getSteps())) {
      IvyToolStep step = new IvyToolStep();
      step.setStepNo(0);
      step.setResult("<iframe>" + this.startableProcessStart.getLink()
          .queryParams(params).getRelative() + "</iframe>");
      step.setState(RunState.DONE);
      step.setToolName(getName());
      setSteps(Arrays.asList(step));
      setWorkingStepNo(0);
    }
    return BusinessEntityConverter
        .entityToJsonValue(getSteps().get(getWorkingStepNo()));
  }

  @Override
  public ToolType getType() {
    return ToolType.IVY;
  }

  @Override
  public JsonNode buildJsonNode() {
    return BusinessEntityConverter.entityToJsonNode(this);
  }
}
