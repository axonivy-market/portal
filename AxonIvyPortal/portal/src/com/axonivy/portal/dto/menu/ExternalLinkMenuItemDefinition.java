package com.axonivy.portal.dto.menu;

import org.apache.commons.lang3.BooleanUtils;

import com.axonivy.portal.components.enums.MenuKind;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExternalLinkMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = -1699550762106106875L;

  private static final String TARGET_THIS_TAB = "_self";
  private static final String TARGET_NEW_TAB = "_blank";

  private Boolean openInNewTab;

  @Override
  public MenuKind getType() {
    return MenuKind.EXTERNAL_LINK;
  }

  @Override
  public PortalMenuItem convertToPortalMenuItem() {
    if (!hasPermission()) {
      return null;
    }

    String target = BooleanUtils.isTrue(openInNewTab) ? TARGET_NEW_TAB : TARGET_THIS_TAB;

    PortalMenuBuilder builder = new PortalMenuBuilder(
        getTitles().stream().filter(name -> name.getLocale().toLanguageTag().contentEquals(getCurrentLanguage()))
            .findFirst().get().getValue(),
        getType(), false).icon(getIconClass()).cleanParam(true).url(getUrl()).target(target);

    if (getWorkingtaskId() == null) {
      return builder.build();
    }

    return builder.isWorkingOnATask(true).workingTaskId(getWorkingtaskId()).build();
  }

  public Boolean getOpenInNewTab() {
    return openInNewTab;
  }

  public void setOpenInNewTab(Boolean openInNewTab) {
    this.openInNewTab = openInNewTab;
  }
}