package com.axonivy.portal.dto.menu;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.menu.management.enums.MenuSource;

/**
 * A single entry in the {@code Portal.Menu.Order} manifest: which item, from which source,
 * and whether it is shown in the sidebar. Ordering is positional (the index within
 * {@link MenuOrder#getMenus()}); the version lives on the {@link MenuOrder} wrapper.
 *
 * <p>{@code visibility} is the string {@code "true"} or {@code "false"}. Missing, null or blank
 * means visible, so manifests written before the field existed keep showing every entry. Only
 * {@code "false"} hides an entry, and a hidden entry keeps its position.
 */
public class MenuOrderEntry implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final String VISIBLE = "true";
  private static final String HIDDEN = "false";

  private String id;
  private MenuSource source;
  private String visibility = VISIBLE;

  public MenuOrderEntry() {}

  public MenuOrderEntry(String id, MenuSource source) {
    this(id, source, true);
  }

  public MenuOrderEntry(String id, MenuSource source, boolean visible) {
    this.id = id;
    this.source = source;
    this.visibility = toVisibilityValue(visible);
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

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public boolean visible() {
    return !HIDDEN.equalsIgnoreCase(StringUtils.trim(visibility));
  }

  public static String toVisibilityValue(boolean visible) {
    return visible ? VISIBLE : HIDDEN;
  }
}
