package com.axonivy.portal.dto.menu;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StaticPageMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = 1L;

  @Override
  public MenuKind getType() {
    return MenuKind.STATIC_PAGE;
  }

  @Override
  public PortalMenuItem convertToPortalMenuItem() {
    if (!hasPermission()) {
      return null;
    }

    String staticPageUrl = PortalNavigatorAPI.buildPortalStaticPageUrl(getUrl());

    PortalMenuBuilder builder = new PortalMenuBuilder(
        getTitles().stream().filter(name -> name.getLocale().toLanguageTag().contentEquals(getCurrentLanguage()))
            .findFirst().get().getValue(),
        getType(), false).icon(getIconClass()).cleanParam(true).url(staticPageUrl);

    if (getWorkingtaskId() == null) {
      return builder.build();
    }

    return builder.isWorkingOnATask(true).workingTaskId(getWorkingtaskId()).build();
  }
}
