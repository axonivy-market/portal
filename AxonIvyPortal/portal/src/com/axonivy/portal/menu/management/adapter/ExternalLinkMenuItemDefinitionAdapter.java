package com.axonivy.portal.menu.management.adapter;

import java.util.ArrayList;
import java.util.Optional;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.CustomSubMenuItemService;

import ch.ivyteam.ivy.security.ISecurityConstants;

public class ExternalLinkMenuItemDefinitionAdapter
    implements IMenuItemDefinitionAdapter<ExternalLinkMenuItemDefinition, CustomSubMenuItem> {

  private static final ExternalLinkMenuItemDefinitionAdapter instance = new ExternalLinkMenuItemDefinitionAdapter();

  public static ExternalLinkMenuItemDefinitionAdapter getInstance() {
    return instance;
  }

  @Override
  public ExternalLinkMenuItemDefinition toMenuDefinition(CustomSubMenuItem source, MenuSource type) {
    ExternalLinkMenuItemDefinition menu = new ExternalLinkMenuItemDefinition();
    menu.setSource(type);
    menu.setId(source.getId());
    menu.setDisplayTitle(source.getLabel());
    menu.setUrl(source.getLink());
    menu.setIndex(source.getIndex());
    menu.setVersion(source.getVersion());
    menu.setIncludedIconFamily(hasIconFamily(source.getIcon()));
    menu.setIcon(removeIconFamily(source.getIcon()));
    menu.setDisplayTitle(source.getLabel());
    menu.setTitles(initAndSetValue(menu.getDisplayTitle()));

    // By default, custom menu is visible to Everybody
    menu.setPermissions(new ArrayList<>());
    menu.getPermissions().add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
    return menu;
  }


  @Override
  public CustomSubMenuItem toSource(ExternalLinkMenuItemDefinition menu) {
    CustomSubMenuItem source = CustomSubMenuItemService.loadFromConfiguration().stream()
        .filter(customMenu -> Optional.ofNullable(customMenu).map(CustomSubMenuItem::getId).isPresent())
        .filter(customMenu -> customMenu.getId().equals(menu.getId())).findFirst().orElse(null);
    if (source == null) {
      source = new CustomSubMenuItem();
      source.setId(menu.getId());
      source.setVersion(menu.getVersion());
      source.setMenuKind(MenuKind.EXTERNAL_LINK);
      source.setIsExternalLink(true);
    }

    source.setLink(menu.getUrl());
    source.setIndex(menu.getIndex());
    source.setIcon(menu.isIncludedIconFamily() ? addIconFamily(menu.getIcon()) : menu.getIcon());
    source.setLabel(menu.getDisplayTitle());
    return source;
  }
}
