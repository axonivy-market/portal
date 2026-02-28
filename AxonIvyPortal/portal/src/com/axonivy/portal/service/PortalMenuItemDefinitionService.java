package com.axonivy.portal.service;

import java.util.List;

import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalMenuItemDefinitionService extends JsonConfigurationService<PortalMenuItemDefinition> {

  private static final String EMPTY_ARRAY = "[]";
  private static PortalMenuItemDefinitionService instance;

  public static PortalMenuItemDefinitionService getInstance() {
    if (instance == null) {
      instance = new PortalMenuItemDefinitionService();
    }
    return instance;
  }

  @Override
  public Class<PortalMenuItemDefinition> getType() {
    return PortalMenuItemDefinition.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.MENU.key;
  }

  @Override
  public List<PortalMenuItemDefinition> saveAllPublicConfig(List<PortalMenuItemDefinition> entities) {
    Ivy.var().set(getConfigKey(), EMPTY_ARRAY);
    return super.saveAllPublicConfig(entities);
  }
}