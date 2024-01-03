package com.axonivy.portal.developerexamples.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseUtils {

  private CaseUtils() {}

  public static ICase findCase(String caseId) {
    return Sudo.get(() -> Ivy.wf().findCase(caseId));
  }
  
}
