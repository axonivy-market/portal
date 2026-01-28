package com.axonivy.portal.dto.menu;

import java.util.Arrays;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StandardMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = -2921340216813270504L;

  private StandardMenuItemDefinitionType standardType;

  public StandardMenuItemDefinition() {
  };

  public StandardMenuItemDefinition(StandardMenuItemDefinitionType type) {
    this.standardType = type;
    this.setPermissions(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME));
  }

  @Override
  public MenuKind getType() {
    return MenuKind.STANDARD;
  }

  @Override
  public PortalMenuItem convertToPortalMenuItem() {
    setOnClick(getStandardType().getOnClick());

    MenuKind updatedKind = MenuKind.STANDARD;
    if (standardType == StandardMenuItemDefinitionType.PROCESS) {
      updatedKind = MenuKind.PROCESS;
    }
    PortalMenuBuilder builder = new PortalMenuBuilder(Ivy.cms().co(getStandardType().getCmsUri()), updatedKind, false)
        .icon(getStandardType().getIcon()).onClick(getStandardType().getOnClick()).url("#");

    if (getWorkingtaskId() == null) {
      return builder.build();
    }

    return builder.isWorkingOnATask(true).workingTaskId(getWorkingtaskId()).build();
  }

  public StandardMenuItemDefinitionType getStandardType() {
    return standardType;
  }
}