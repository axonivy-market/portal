package com.axonivy.portal.component.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.component.dto.IvyProcessResultDTO;
import com.axonivy.portal.component.service.IProcessService;
import com.axonivy.portal.component.util.IvyExecutor;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService implements IProcessService {

  private static final String PORTAL_IN_TEAMS_REQUEST_PATH = "InTeams.ivp";
  private static final String PORTAL_START_REQUEST_PATH = "/DefaultApplicationHomePage.ivp";

  private ProcessService() {}

  public static ProcessService newInstance() {
    return new ProcessService();
  }

  @Override
  public IvyProcessResultDTO findProcesses() {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyProcessResultDTO result = new IvyProcessResultDTO();
      result.setProcesses(findStartablesWithoutPortalHomeAndMSTeamsProcess(Ivy.session()));
      return result;
    });
  }

  private List<IWebStartable> findStartablesWithoutPortalHomeAndMSTeamsProcess(IWorkflowSession session) {
    return session.getStartables().stream()
        .filter(process -> isNotPortalHomeAndMSTeamsProcess(process))
        .collect(Collectors.toList());
  }

  private boolean isNotPortalHomeAndMSTeamsProcess(IWebStartable process) {
    String relativeEncoded = process.getLink().getRelativeEncoded();
    return !relativeEncoded.endsWith(PORTAL_START_REQUEST_PATH) && !relativeEncoded.endsWith(PORTAL_IN_TEAMS_REQUEST_PATH);
  }

}
