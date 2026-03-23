package com.axonivy.portal.migration.migrator;

import com.axonivy.portal.enums.SidebarBehaviour;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;

public class OldVariableMigrator {

  private static final String OLD_KEEP_SIDEBAR_EXPANDED_KEY = "Portal.KeepSidebarExpanded";

  public static void migrateSidebarSetting() {
    String oldValue = Ivy.var().get(OLD_KEEP_SIDEBAR_EXPANDED_KEY);
    if (Boolean.parseBoolean(oldValue)) {
      String newKey = GlobalVariable.SIDEBAR_BEHAVIOUR.getKey();
      String currentNewValue = Ivy.var().get(newKey);
      if (SidebarBehaviour.HOVER.name().equals(currentNewValue)) {
        Ivy.var().set(newKey, SidebarBehaviour.STICK.name());
        Ivy.log().info("Migrated Portal.KeepSidebarExpanded=true to Portal.Sidebar.Behaviour=STICK");
      }
      Ivy.var().set(OLD_KEEP_SIDEBAR_EXPANDED_KEY, "false");
    }
  }
}
