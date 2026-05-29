package com.axonivy.portal.dto.menu;

import java.io.Serializable;

import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

/**
 * Lean entry persisted in {@code Portal.Menu.Order}. Carries only the source-side
 * reference ({@code id}) and which source it lives in. Display data (title, icon,
 * permissions, url) is looked up from the source on demand by the loader.
 *
 * Order is the entry's position in the list — no index field.
 */
public class MenuOrderEntry extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 1L;

  private MenuSource source;

  public MenuOrderEntry() {}

  public MenuOrderEntry(String id, MenuSource source, String version) {
    setId(id);
    setVersion(version);
    this.source = source;
  }

  public MenuSource getSource() {
    return source;
  }

  public void setSource(MenuSource source) {
    this.source = source;
  }
}
