package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.ai.AiTool;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;

public class AiToolService extends JsonConfigurationService<AiTool> {

  private static AiToolService instance;

  public static AiToolService getInstance() {
    if (instance == null) {
      instance = new AiToolService();
    }
    return instance;
  }

  @Override
  public Class<AiTool> getType() {
    return AiTool.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.AI_TOOL.key;
  }

  @Override
  public List<AiTool> getPublicConfig() {
    String jsonValue = Ivy.var().get(getConfigKey());
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }

    return BusinessEntityConverter.jsonValueToEntities(jsonValue, getType());
  }
}