package com.axonivy.portal.components.util;

import java.util.Optional;

import com.axonivy.portal.components.enums.PortalLibrary;

import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;

public final class CaseUtils {

  private CaseUtils() {}

  public static ICase findCase(long caseId) {
    return Sudo.get(() -> Ivy.wf().findCase(caseId));
  }
  
  public static ICase findCase(String uuid) {
    return Sudo.get(() -> Ivy.wf().findCase(uuid));
  }
  
  public static boolean isExpressCase(ICase caze) {
    return Optional.ofNullable(caze)
        .map(ICase::getBusinessCase)
        .map(IBusinessCase::getProcessModelVersion)
        .map(IWorkflowProcessModelVersion::getLibrary)
        .map(ILibrary::getId).orElse("")
        .endsWith(PortalLibrary.AXON_EXPRESS.getProjectId());
  }
}