package com.axonivy.portal.service;

import java.util.List;

import com.axonivy.portal.dto.menu.MenuOrderEntry;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;

/**
 * Persistence for the {@code Portal.Menu.Order} manifest.
 * Public-only — the order is a portal-wide setting, not per-user.
 * Reads the Ivy variable directly on every call so external changes
 * (Cockpit, another cluster node) are visible immediately without restart.
 */
public class MenuOrderService extends JsonConfigurationService<MenuOrderEntry> {

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
  public List<MenuOrderEntry> findAll() {
    return getPublicConfig();
  }

  @Override
  public List<MenuOrderEntry> saveAllPublicConfig(List<MenuOrderEntry> entities) {
    savePublicConfig(entities);
    return entities;
  }
}
