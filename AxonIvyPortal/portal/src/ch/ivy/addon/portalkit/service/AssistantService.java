package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.ai.Assistant;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;

public class AssistantService extends JsonConfigurationService<Assistant> {

  private static AssistantService instance;

  public static AssistantService getInstance() {
    if (instance == null) {
      instance = new AssistantService();
    }
    return instance;
  }

  @Override
  public Class<Assistant> getType() {
    return Assistant.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.AI_ASSISTANT.key;
  }

  @Override
  public List<Assistant> getPublicConfig() {
    String jsonValue = Ivy.var().get(getConfigKey());
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }

    return BusinessEntityConverter.jsonValueToEntities(jsonValue, getType());
  }
}
