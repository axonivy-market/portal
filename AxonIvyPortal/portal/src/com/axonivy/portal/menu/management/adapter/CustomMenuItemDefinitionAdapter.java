package com.axonivy.portal.menu.management.adapter;

import java.util.ArrayList;
import java.util.Optional;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.CustomSubMenuItemService;

import ch.ivyteam.ivy.security.ISecurityConstants;

public class CustomMenuItemDefinitionAdapter
    implements IMenuItemDefinitionAdapter<CustomMenuItemDefinition, CustomSubMenuItem> {

  private static final CustomMenuItemDefinitionAdapter instance = new CustomMenuItemDefinitionAdapter();

  public static CustomMenuItemDefinitionAdapter getInstance() {
    return instance;
  }

  @Override
  public CustomMenuItemDefinition toMenuDefinition(CustomSubMenuItem source, MenuSource type) {
    CustomMenuItemDefinition menu = new CustomMenuItemDefinition();
    menu.setSource(type);
    menu.setId(source.getId());
    menu.setProcessStartPath(source.getLink());
    menu.setIcon(source.getIcon());
    menu.setIndex(source.getIndex());
    menu.setVersion(source.getVersion());
    menu.setIncludedIconFamily(hasIconFamily(source.getIcon()));
    menu.setIcon(removeIconFamily(source.getIcon()));
    menu.setDisplayTitle(source.getLabel());
    menu.setTitles(initAndSetValue(menu.getDisplayTitle()));

    // By default, custom menu is visible to Everybody
    menu.setPermissions(new ArrayList<>());
    menu.getPermissions().add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);

    // The process start is intentionally NOT resolved here: this adapter runs on the
    // sidebar render path for every user and resolving would scan all startables per
    // item. The sidebar only needs processStartPath; the admin edit dialog resolves
    // the process lazily (MenuDetailsBean.onEditMenu).
    return menu;
  }

  @Override
  public CustomSubMenuItem toSource(CustomMenuItemDefinition menu) {
    CustomSubMenuItem source = CustomSubMenuItemService.loadFromConfiguration().stream()
        .filter(customMenu -> Optional.ofNullable(customMenu).map(CustomSubMenuItem::getId).isPresent())
        .filter(customMenu -> customMenu.getId().equals(menu.getId()))
        .findFirst().orElse(null);

    if (source == null) {
      source = new CustomSubMenuItem();
      source.setId(menu.getId());
      source.setVersion(menu.getVersion());
      source.setMenuKind(MenuKind.CUSTOM);
      source.setIsExternalLink(false);
    }

    source.setLink(menu.getProcessStartPath());
    source.setIndex(menu.getIndex());
    source.setLabel(menu.getDisplayTitle());
    source.setIcon(menu.isIncludedIconFamily() ? addIconFamily(menu.getIcon()) : menu.getIcon());

    return source;
  }
}
