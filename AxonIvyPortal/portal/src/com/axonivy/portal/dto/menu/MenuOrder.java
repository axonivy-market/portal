package com.axonivy.portal.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper persisted under the {@code Portal.Menu.Order} variable. Stored as a JSON
 * object — {@code {"version": "...", "menus": [{"id": "...", "source": "..."}]}} —
 * rather than a bare array, so the top level carries the schema version (the migration
 * hook) and the ordered entries live under {@code menus}.
 */
public class MenuOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  private String version;
  private List<MenuOrderEntry> menus = new ArrayList<>();

  public MenuOrder() {}

  public MenuOrder(String version, List<MenuOrderEntry> menus) {
    this.version = version;
    this.menus = menus != null ? menus : new ArrayList<>();
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public List<MenuOrderEntry> getMenus() {
    return menus;
  }

  public void setMenus(List<MenuOrderEntry> menus) {
    this.menus = menus;
  }
}
