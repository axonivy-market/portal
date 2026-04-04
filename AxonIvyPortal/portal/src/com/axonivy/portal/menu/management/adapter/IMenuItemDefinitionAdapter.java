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

  T toMenuDefinition(V source, MenuSource type);
  V toSource(T menu);

  default boolean hasIconFamily(String icon) {
    return StringUtils.isNotBlank(icon)
        && (icon.startsWith(FONT_AWESOME_FAMILY_PREFIX) || icon.startsWith(STREAMLINE_FAMILY_PREFIX));
  }

  default String removeIconFamily(String icon) {
    if (StringUtils.isBlank(icon)) {
      return icon;
    }
    return icon.replace(FONT_AWESOME_FAMILY_PREFIX, StringUtils.EMPTY).replace(STREAMLINE_FAMILY_PREFIX,
          StringUtils.EMPTY);
  }

  default String addIconFamily(String icon) {
    String iconFamily = icon.startsWith(STREAMLINE_ICON_PREFIX) ? STREAMLINE_FAMILY_PREFIX : FONT_AWESOME_FAMILY_PREFIX;
    return iconFamily.concat(icon);
  }

  default List<DisplayName> initAndSetValue(String value) {
    List<DisplayName> result = new ArrayList<>();
    DisplayNameConvertor.initMultipleLanguages(value, result);
    DisplayNameConvertor.setValue(value, result);
    return result;
  }
}
