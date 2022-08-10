package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.environment.Ivy;

public class HiddenTasksCasesConfig {
  public static final String PORTAL_HIDDEN_TASK_CASE_EXCLUDED = "PortalHiddenTaskCaseExcluded";

  private HiddenTasksCasesConfig() {
    
  }

  public static boolean isHiddenTasksCasesExcluded() {
    return Boolean.valueOf(Ivy.var().get(PORTAL_HIDDEN_TASK_CASE_EXCLUDED));
  }
  
}
