package ch.ivy.ws.addon.service;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.WebStartableServiceResult;
import ch.ivy.ws.addon.transformer.IvyWebStartableTransformer;
import ch.ivy.ws.addon.types.IvyWebStartable;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * Default implementation for the web startable service
 */
public class WebStartableServiceImpl extends AbstractService implements IWebStartableService {

  private static final String PORTAL_START_REQUEST_PATH = "/PortalStart.ivp";

  @Override
  public WebStartableServiceResult findWebStartablesByCriteria(WebStartableSearchCriteria searchCriteria,
      String language, Boolean isUrlBuiltFromSystemProperties) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<WebStartableServiceResult>() {
        @Override
        public WebStartableServiceResult call() throws Exception {

          List<IApplication> applications = getAllApplications();
          List<IvyWebStartable> starts = new ArrayList<>();

          Ivy.session().setContentLocale(new Locale(language));

          for (IApplication application : applications) {
            if (searchCriteria.getInvolvedApplications().contains(application.getName())) {
              starts.addAll(getWebStartables(searchCriteria, isUrlBuiltFromSystemProperties, application));
            }
          }
          WebStartableServiceResult result = new WebStartableServiceResult();
          result.setWebStartables(starts);
          return result;
        }

      });
    } catch (Exception e) {
      throw new WSException(10008, e);
    }
  }

  private List<IvyWebStartable> getWebStartables(WebStartableSearchCriteria searchCriteria,
      Boolean isUrlBuiltFromSystemProperties, IApplication application) throws WSException {
    List<IvyWebStartable> starts = new ArrayList<>();
    IWorkflowSession workflowSession = null;
    try {
      workflowSession = getWorkflowSession(searchCriteria, application);
      if (!workflowSession.isSessionUserUnknown()) {
        List<IWebStartable> webStartables = getAllWebStartableFromWorkflow(workflowSession);
        if (searchCriteria.hasKeyword()) {
          starts.addAll(getWebStartableMatchKeyword(searchCriteria, isUrlBuiltFromSystemProperties, webStartables));
        } else {
          starts.addAll(IvyWebStartableTransformer.transform(webStartables, isUrlBuiltFromSystemProperties));
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

  private List<IvyWebStartable> getWebStartableMatchKeyword(WebStartableSearchCriteria searchCriteria,
      Boolean isUrlBuiltFromSystemProperties, List<IWebStartable> webStartables) {
    return webStartables.stream().filter(webStartable -> match(webStartable, searchCriteria.getKeyword()))
        .map(webStartable -> IvyWebStartableTransformer.transform(webStartable, isUrlBuiltFromSystemProperties))
        .collect(Collectors.toList());
  }

  private List<IWebStartable> getAllWebStartableFromWorkflow(IWorkflowSession workflowSession) {
    List<IWebStartable> webStartables = workflowSession.getStartables();
    return webStartables.stream()
        .filter(process -> !process.getLink().getAbsoluteEncoded().endsWith(PORTAL_START_REQUEST_PATH))
        .collect(Collectors.toList());
  }

  private boolean match(IWebStartable webStartable, String keyword) {
    return containsIgnoreCase(webStartable.getDisplayName(), keyword)
        || containsIgnoreCase(webStartable.getDescription(), keyword);
  }

  private IWorkflowSession getWorkflowSession(WebStartableSearchCriteria searchCriteria, IApplication application)
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
