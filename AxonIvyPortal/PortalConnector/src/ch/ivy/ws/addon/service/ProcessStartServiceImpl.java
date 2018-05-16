package ch.ivy.ws.addon.service;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.ProcessStartServiceResult;
import ch.ivy.ws.addon.transformer.IvyProcessStartTransformer;
import ch.ivy.ws.addon.types.IvyProcessStart;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Default implementation for the process start service
 */
public class ProcessStartServiceImpl extends AbstractService implements IProcessStartService {

  private static final String PORTAL_START_REQUEST_PATH = "/PortalStart.ivp";

  @Override
  public ProcessStartServiceResult findProcessStartsByCriteria(ProcessSearchCriteria searchCriteria, String language,
      Boolean isUrlBuiltFromSystemProperties) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<ProcessStartServiceResult>() {
        @Override
        public ProcessStartServiceResult call() throws Exception {

          IvyProcessStartTransformer transformer = new IvyProcessStartTransformer(isUrlBuiltFromSystemProperties);
          List<IApplication> applications = getAllApplications();
          List<IvyProcessStart> starts = new ArrayList<>();

          Ivy.session().setContentLocale(new Locale(language));

          for (IApplication application : applications) {
            if (searchCriteria.getInvolvedApplications().contains(application.getName())) {
              starts.addAll(getProcessStarts(searchCriteria, transformer, application));
            }
          }
          ProcessStartServiceResult result = new ProcessStartServiceResult();
          result.setProcessStarts(starts);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10008, e);
    }
  }

  private List<IvyProcessStart> getProcessStarts(ProcessSearchCriteria searchCriteria,
      IvyProcessStartTransformer transformer, IApplication application) throws WSException {
    List<IvyProcessStart> starts = new ArrayList<>();
    IWorkflowSession workflowSession = null;
    try {
      workflowSession = getWorkflowSession(searchCriteria, application);

      if (!workflowSession.isSessionUserUnknown()) {
        List<IProcessStart> startableProcessStarts = getStartableProcessStarts(workflowSession);
        if (searchCriteria.hasKeyword()) {
          starts.addAll(findProcessStart(startableProcessStarts, searchCriteria, transformer));
        } else {
          starts.addAll(transformer.transform(startableProcessStarts));
        }
      }
    } finally {
      if (workflowSession != null && !workflowSession.isSessionUserSystemUser()) {
        ISecurityContext securityContext = application.getSecurityContext();
        securityContext.destroySession(workflowSession.getIdentifier());
      }
    }
    return starts;
  }

  private List<IProcessStart> getStartableProcessStarts(IWorkflowSession workflowSession) {
    List<IProcessStart> startableProcessStarts = workflowSession.getStartableProcessStarts();
    return startableProcessStarts.stream()
        .filter(process -> !process.getRequestPath().endsWith(PORTAL_START_REQUEST_PATH)).collect(Collectors.toList());
  }

  private List<IvyProcessStart> findProcessStart(List<IProcessStart> startableProcessStarts,
      ProcessSearchCriteria searchCriteria, IvyProcessStartTransformer transformer) {
    return startableProcessStarts.stream().filter(processStart -> match(processStart, searchCriteria.getKeyword()))
        .map(transformProcessStart(transformer)).collect(Collectors.toList());
  }

  private Function<? super IProcessStart, ? extends IvyProcessStart> transformProcessStart(IvyProcessStartTransformer transformer) {
    return processStart -> transformer.transform(processStart); //NOSONAR
  }
  
  private boolean match(IProcessStart processStart, String keyword) {
    return containsIgnoreCase(processStart.getName(), keyword)
        || containsIgnoreCase(processStart.getDescription(), keyword);
  }

  private IWorkflowSession getWorkflowSession(ProcessSearchCriteria searchCriteria, IApplication application)
      throws WSException {
    if (searchCriteria.hasInvolvedUsername()) {
      return findUserWorkflowSession(searchCriteria.getInvolvedUsername(), application);
    }

    return Ivy.wf().getWorkflowSession(application.getSecurityContext().getSystemUserSession());
  }

  private static List<IApplication> getAllApplications() {
    return ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
  }
}
