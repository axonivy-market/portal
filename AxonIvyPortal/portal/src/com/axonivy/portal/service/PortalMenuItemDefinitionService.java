package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

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
  @SuppressWarnings("unchecked")
  public List<PortalMenuItemDefinition> findAll() {
    String sessionUserId = getSessionUserId();
    IvyCacheService cacheService = IvyCacheService.getInstance();
    List<PortalMenuItemDefinition> cached = null;
    try {
      cached = (List<PortalMenuItemDefinition>) cacheService
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_MENU, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_MENU, sessionUserId);
    }

    if (cached == null) {
      synchronized (sessionUserId.intern()) {
        cached = super.findAll();
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_MENU, sessionUserId, cached);
      }
    }
    return new ArrayList<>(cached);
  }

  @Override
  public List<PortalMenuItemDefinition> saveAllPublicConfig(List<PortalMenuItemDefinition> entities) {
    Ivy.var().set(getConfigKey(), EMPTY_ARRAY);
    List<PortalMenuItemDefinition> saved = super.saveAllPublicConfig(entities);
    invalidateCache();
    return saved;
  }

  public static void invalidateCache() {
    IvyCacheService.getInstance().invalidateSessionEntry(IvyCacheIdentifier.PORTAL_MENU, getSessionUserId());
  }

  private static String getSessionUserId() {
    String key = SessionAttribute.SESSION_IDENTIFIER.name();
    IWorkflowSession session = Ivy.session();
    if (session.getAttribute(key) == null) {
      session.setAttribute(key, UUID.randomUUID().toString());
    }
    return (String) session.getAttribute(key);
  }
}
