package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.IProcessService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService implements IProcessService {

  private static final String PORTAL_START_REQUEST_PATH = "/DefaultApplicationHomePage.ivp";

  private ProcessService() {}

  public static ProcessService newInstance() {
    return new ProcessService();
  }

  @Override
  public IvyProcessResultDTO findProcesses() {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyProcessResultDTO result = new IvyProcessResultDTO();
      result.setProcesses(findStartablesWithoutPortalHome(Ivy.session()));
      return result;
    });
  }

  private List<IWebStartable> findStartablesWithoutPortalHome(IWorkflowSession session) {
    return session.getStartables().stream()
        .filter(process -> !process.getLink().getRelativeEncoded().endsWith(PORTAL_START_REQUEST_PATH))
        .collect(Collectors.toList());
  }

}
