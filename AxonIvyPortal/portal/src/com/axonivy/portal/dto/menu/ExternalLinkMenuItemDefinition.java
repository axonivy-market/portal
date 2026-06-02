package com.axonivy.portal.dto.menu;

import com.axonivy.portal.components.enums.MenuKind;

public class ExternalLinkMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = -1699550762106106875L;

  private Boolean openInNewTab;

  @Override
  public MenuKind getType() {
    return MenuKind.EXTERNAL_LINK;
  }

  public Boolean getOpenInNewTab() {
    return openInNewTab;
  }

  public void setOpenInNewTab(Boolean openInNewTab) {
    this.openInNewTab = openInNewTab;
  }
}
