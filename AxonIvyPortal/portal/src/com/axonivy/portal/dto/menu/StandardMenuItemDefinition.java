package com.axonivy.portal.dto.menu;

import java.util.Arrays;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;

import ch.ivyteam.ivy.security.ISecurityConstants;

public class StandardMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = -2921340216813270504L;

  private StandardMenuItemDefinitionType standardType;

  public StandardMenuItemDefinition() {}

  public StandardMenuItemDefinition(StandardMenuItemDefinitionType type) {
    this.standardType = type;
    setPermissions(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME));
    setOnClick(type.getOnClick());
  }

  @Override
  public MenuKind getType() {
    return MenuKind.STANDARD;
  }

  public StandardMenuItemDefinitionType getStandardType() {
    return standardType;
  }

  public void setStandardType(StandardMenuItemDefinitionType standardType) {
    this.standardType = standardType;
  }
}
