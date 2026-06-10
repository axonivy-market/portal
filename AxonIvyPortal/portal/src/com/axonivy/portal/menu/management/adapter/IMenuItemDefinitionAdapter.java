package com.axonivy.portal.menu.management.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;

public interface IMenuItemDefinitionAdapter<T extends PortalMenuItemDefinition, V> {

  static final String STREAMLINE_FAMILY_PREFIX = "si ";
  static final String STREAMLINE_ICON_PREFIX = "si-";
  static final String FONT_AWESOME_FAMILY_PREFIX = "fa ";
  static final String TABLER_FAMILY_PREFIX = "ti ";
  static final String TABLER_ICON_PREFIX = "ti-";
  static final String TABLER_FILLED_FAMILY_PREFIX = "tif ";
  static final String TABLER_FILLED_ICON_PREFIX = "tif-";

  T toMenuDefinition(V source, MenuSource type);
  V toSource(T menu);

  default boolean hasIconFamily(String icon) {
    return StringUtils.isNotBlank(icon)
        && (icon.startsWith(FONT_AWESOME_FAMILY_PREFIX)
            || icon.startsWith(STREAMLINE_FAMILY_PREFIX)
            || icon.startsWith(TABLER_FILLED_FAMILY_PREFIX)
            || icon.startsWith(TABLER_FAMILY_PREFIX));
  }

  default String removeIconFamily(String icon) {
    if (StringUtils.isBlank(icon)) {
      return icon;
    }
    // Strip only the leading family token: replace() would also mangle icon names
    // that contain a family string elsewhere (e.g. modifier classes).
    for (String family : new String[] {FONT_AWESOME_FAMILY_PREFIX, STREAMLINE_FAMILY_PREFIX,
        TABLER_FILLED_FAMILY_PREFIX, TABLER_FAMILY_PREFIX}) {
      if (icon.startsWith(family)) {
        return icon.substring(family.length());
      }
    }
    return icon;
  }

  default String addIconFamily(String icon) {
    if (StringUtils.isBlank(icon) || hasIconFamily(icon)) {
      // Idempotent: don't re-prefix an icon that already carries its family.
      return icon;
    }
    if (icon.startsWith(STREAMLINE_ICON_PREFIX)) {
      return STREAMLINE_FAMILY_PREFIX.concat(icon);
    }
    if (icon.startsWith(TABLER_FILLED_ICON_PREFIX)) {
      return TABLER_FILLED_FAMILY_PREFIX.concat(icon);
    }
    if (icon.startsWith(TABLER_ICON_PREFIX)) {
      return TABLER_FAMILY_PREFIX.concat(icon);
    }
    return FONT_AWESOME_FAMILY_PREFIX.concat(icon);
  }

  default List<DisplayName> initAndSetValue(String value) {
    List<DisplayName> result = new ArrayList<>();
    DisplayNameConvertor.initMultipleLanguages(value, result);
    DisplayNameConvertor.setValue(value, result);
    return result;
  }
}
