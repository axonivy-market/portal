package com.axonivy.portal.menu.management.adapter;

import java.util.Optional;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.StaticPageMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.CustomSubMenuItemService;

public class StaticPageMenuItemDefinitionAdapter
    implements IMenuItemDefinitionAdapter<StaticPageMenuItemDefinition, CustomSubMenuItem> {

  private static final StaticPageMenuItemDefinitionAdapter instance = new StaticPageMenuItemDefinitionAdapter();

  public static StaticPageMenuItemDefinitionAdapter getInstance() {
    return instance;
  }

  @Override
  public StaticPageMenuItemDefinition toMenuDefinition(CustomSubMenuItem source, MenuSource type) {
    StaticPageMenuItemDefinition menu = new StaticPageMenuItemDefinition();
    menu.setSource(type);
    menu.setId(source.getId());
    menu.setDescription(source.getLabel());
    menu.setUrl(source.getLink());
    menu.setIndex(source.getIndex());
    menu.setVersion(source.getVersion());
    menu.setIncludedIconFamily(hasIconFamily(source.getIcon()));
    menu.setIcon(removeIconFamily(source.getIcon()));
    menu.setDisplayTitle(source.getLabel());
    menu.setTitles(initAndSetValue(menu.getDisplayTitle()));
    return menu;
  }

  @Override
  public CustomSubMenuItem toSource(StaticPageMenuItemDefinition menu) {
    CustomSubMenuItem source = CustomSubMenuItemService.loadFromConfiguration().stream()
        .filter(customMenu -> Optional.ofNullable(customMenu).map(CustomSubMenuItem::getId).isPresent())
        .filter(customMenu -> customMenu.getId().equals(menu.getId())).findFirst().orElse(null);
    if (source == null) {
      source = new CustomSubMenuItem();
      source.setId(menu.getId());
      source.setVersion(menu.getVersion());
      source.setMenuKind(MenuKind.STATIC_PAGE);
      source.setIsExternalLink(false);
    }

    source.setLink(menu.getUrl());
    source.setIndex(menu.getIndex());
    source.setLabel(menu.getDescription());
    source.setIcon(menu.isIncludedIconFamily() ? addIconFamily(menu.getIcon()) : menu.getIcon());
    source.setLabel(menu.getDisplayTitle());
    return source;
  }
}
