package com.axonivy.portal.userexamples.util;

import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.components.enums.AdditionalProperty;
import com.axonivy.portal.components.publicapi.RoleAPI;

import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;

public class RoleUtils {
  private static final String[] DEFAULT_HIDDEN_ROLES = {"AXONIVY_PORTAL_ADMIN"};
  public static void setHidePropertyForDefaultHiddenRoles() {
    List<String> defaultHiddenRoleNames = Arrays.asList(DEFAULT_HIDDEN_ROLES);
    String hideProperty = AdditionalProperty.HIDE.toString();

    defaultHiddenRoleNames.forEach(roleName -> {
      IRole role = findRole(roleName);
      if (role != null && role.getProperty(hideProperty) == null) {
        RoleAPI.setProperty(role, hideProperty, hideProperty);
      }
    });
  }

  public static IRole findRole(String name) {
    return Sudo.get(() -> {
      return ISecurityContext.current().roles().find(name);
    });
  }

}
