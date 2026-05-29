package com.axonivy.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.axonivy.portal.dto.menu.MenuOrderEntry;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Persistence + session cache for the {@code Portal.Menu.Order} manifest.
 * Public-only — the order is a portal-wide setting, not per-user.
 */
public class MenuOrderService extends JsonConfigurationService<MenuOrderEntry> {

  private static final String NO_SESSION_CACHE_KEY = "no-session";
  private static MenuOrderService instance;

  public static MenuOrderService getInstance() {
    if (instance == null) {
      instance = new MenuOrderService();
    }
    return instance;
  }

  @Override
  public Class<MenuOrderEntry> getType() {
    return MenuOrderEntry.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.MENU_ORDER.key;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<MenuOrderEntry> findAll() {
    String sessionUserId = getSessionUserId();
    IvyCacheService cacheService = IvyCacheService.getInstance();
    List<MenuOrderEntry> cached = null;
    try {
      cached = (List<MenuOrderEntry>) cacheService
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_MENU_ORDER, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_MENU_ORDER, sessionUserId);
    }

    if (cached == null) {
      synchronized (sessionUserId.intern()) {
        cached = getPublicConfig();
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_MENU_ORDER, sessionUserId, cached);
      }
    }
    return new ArrayList<>(cached);
  }

  @Override
  public List<MenuOrderEntry> saveAllPublicConfig(List<MenuOrderEntry> entities) {
    savePublicConfig(entities);
    invalidateCache();
    return entities;
  }

  public static void invalidateCache() {
    IvyCacheService.getInstance().invalidateSessionEntry(IvyCacheIdentifier.PORTAL_MENU_ORDER, getSessionUserId());
  }

  /**
   * Returns the cache key for the current Ivy session, or a stable fallback when
   * called from a non-session context (background job, system thread). Without this
   * guard, {@code findAll} / {@code invalidateCache} would NPE on {@code Ivy.session()}.
   */
  private static String getSessionUserId() {
    IWorkflowSession session = Ivy.session();
    if (session == null) {
      return NO_SESSION_CACHE_KEY;
    }
    String key = SessionAttribute.SESSION_IDENTIFIER.name();
    if (session.getAttribute(key) == null) {
      session.setAttribute(key, UUID.randomUUID().toString());
    }
    return (String) session.getAttribute(key);
  }
}
