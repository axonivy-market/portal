package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.IProcessService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
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

      List<PortalIvyDataException> errors = new ArrayList<>();
      List<IWebStartable> processes = new ArrayList<>();
      IWorkflowSession session = Ivy.session();
      IApplication application = Ivy.request().getApplication();
      try {
        processes.addAll(findStartablesWithoutPortalHome(session));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting processes of user {0} within app {1}", ex, Ivy.session().getSessionUserName(), application.getName());
        errors.add(new PortalIvyDataException(application.getName(), PortalIvyDataErrorType.FAIL_TO_LOAD_PROCESS.toString()));
      }
      result.setErrors(errors);
      result.setProcesses(processes);
      return result;
    });
  }

  private List<IWebStartable> findStartablesWithoutPortalHome(IWorkflowSession session) {
    return session.getStartables().stream()
        .filter(process -> !process.getLink().getRelativeEncoded().endsWith(PORTAL_START_REQUEST_PATH))
        .collect(Collectors.toList());
  }

}
