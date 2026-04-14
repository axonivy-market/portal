package com.axonivy.portal.menu.management.adapter;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.util.MenuUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;

public class ThirdPartyAppMenuItemDefinitionAdapter
    implements IMenuItemDefinitionAdapter<ExternalLinkMenuItemDefinition, Application> {

  private static final ThirdPartyAppMenuItemDefinitionAdapter instance = new ThirdPartyAppMenuItemDefinitionAdapter();

  public static ThirdPartyAppMenuItemDefinitionAdapter getInstance() {
    return instance;
  }

  @Override
  public ExternalLinkMenuItemDefinition toMenuDefinition(Application source, MenuSource type) {
    ExternalLinkMenuItemDefinition menu = new ExternalLinkMenuItemDefinition();
    menu.setSource(MenuSource.THIRD_PARTY_APP_CONFIGURATION);
    menu.setId(source.getId());
    menu.setPermissions(source.getPermissions());
    menu.setUrl(source.getLink());
    menu.setIsPublic(source.getIsPublic());
    menu.setIncludedIconFamily(hasIconFamily(source.getMenuIcon()));
    menu.setIcon(removeIconFamily(source.getMenuIcon()));
    menu.setIndex(source.getMenuOrdinal());
    menu.setTitles(jsonToDisplayNames(source.getDisplayName()));
    menu.setDisplayTitle(MenuUtils.getDisplayTitle(menu));
    return menu;
  }

  private List<DisplayName> jsonToDisplayNames(String displayName) {
    Map<String, String> displayNames = DisplayNameConvertor.parseJson(displayName).getDisplayNameAsMap();
    List<DisplayName> titles = LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages().stream()
        .map(lang -> new DisplayName(Locale.forLanguageTag(lang), displayNames.get(lang))).collect(Collectors.toList());
    return titles;
  }

  private String displayNameToJson(List<DisplayName> displayNames) {
    DisplayNameConvertor converter = new DisplayNameConvertor();
    for (DisplayName displayName : displayNames) {
      converter.add(displayName.getLocale(), displayName.getValue());
    }
    return converter.toJson();
  }

  @Override
  public Application toSource(ExternalLinkMenuItemDefinition menu) {
    Application source = Optional.ofNullable(RegisteredApplicationService.getInstance().findById(menu.getId()))
        .orElse(null);

    if (source == null) {
      source = new Application();
      source.setId(menu.getId());
      source.setVersion(menu.getVersion());
      source.setIsPublic(menu.getIsPublic());
    }

    source.setPermissions(menu.getPermissions());
    source.setLink(menu.getUrl());
    source.setMenuOrdinal(menu.getIndex());
    source.setMenuIcon(menu.isIncludedIconFamily() ? addIconFamily(menu.getIcon()) : menu.getIcon());
    source.setDisplayName(displayNameToJson(menu.getTitles()));
    return source;
  }
}
