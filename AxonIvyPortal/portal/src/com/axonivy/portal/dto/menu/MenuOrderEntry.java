package com.axonivy.portal.dto.menu;

import java.io.Serializable;

import com.axonivy.portal.menu.management.enums.MenuSource;

/**
 * A single entry in the {@code Portal.Menu.Order} manifest: which item, from which
 * source. Ordering is positional (the index within {@link MenuOrder#getMenus()}); the
 * version lives on the {@link MenuOrder} wrapper, so an entry is just id + source.
 */
public class MenuOrderEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private MenuSource source;

  public MenuOrderEntry() {}

  public MenuOrderEntry(String id, MenuSource source) {
    this.id = id;
    this.source = source;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MenuSource getSource() {
    return source;
  }

  public void setSource(MenuSource source) {
    this.source = source;
  }
}
