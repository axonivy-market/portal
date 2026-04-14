package com.axonivy.portal.migration.migrator;

import com.axonivy.portal.enums.SidebarMode;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;

public class GlobalVariableMigrator {

  private static final String OLD_KEEP_SIDEBAR_EXPANDED_KEY = "Portal.KeepSidebarExpanded";

  public static void migrateSidebarSetting() {
    String newKey = GlobalVariable.SIDEBAR_MODE.getKey();
    String newValue = Ivy.var().get(newKey);
    boolean isOldKeyEnabled = Boolean.parseBoolean(Ivy.var().get(OLD_KEEP_SIDEBAR_EXPANDED_KEY));
    boolean isNewKeyDefault = SidebarMode.HOVER.name().equals(newValue);
    if (isOldKeyEnabled && isNewKeyDefault) {
      Ivy.var().set(newKey, SidebarMode.STICK.name());
      Ivy.var().set(OLD_KEEP_SIDEBAR_EXPANDED_KEY, "false");
      Ivy.log().info("Migrated Portal.KeepSidebarExpanded=true to Portal.Sidebar.Mode=STICK");
    }
  }
}
