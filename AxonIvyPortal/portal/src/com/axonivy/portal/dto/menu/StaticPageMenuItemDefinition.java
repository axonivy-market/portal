package com.axonivy.portal.dto.menu;

import com.axonivy.portal.components.enums.MenuKind;

public class StaticPageMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = 1L;

  @Override
  public MenuKind getType() {
    return MenuKind.STATIC_PAGE;
  }
}
