package com.axonivy.portal.components.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;

public final class CaseUtils {

  private CaseUtils() {}

  public static ICase findCase(long caseId) {
    return Sudo.get(() -> Ivy.wf().findCase(caseId));
  }
  
  public static ICase findCase(String uuid) {
    return Sudo.get(() -> Ivy.wf().findCase(uuid));
  }
}