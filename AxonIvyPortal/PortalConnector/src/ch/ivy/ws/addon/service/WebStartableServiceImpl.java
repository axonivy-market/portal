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
              IWorkflowSession workflowSession = null;
              try {
                workflowSession = getWorkflowSession(searchCriteria, application);

                if (!workflowSession.isSessionUserUnknown()) {
                  List<IWebStartable> webStartables = workflowSession.getStartables();
                  webStartables =
                      webStartables
                          .stream()
                          .filter(
                              process -> !process.getLink().getAbsoluteEncoded().endsWith(PORTAL_START_REQUEST_PATH))
                          .collect(Collectors.toList());
                  if (searchCriteria.hasKeyword()) {
                    starts.addAll(webStartables
                        .stream()
                        .filter(webStartable -> match(webStartable, searchCriteria.getKeyword()))
                        .map(
                            webStartable -> IvyWebStartableTransformer.transform(webStartable,
                                isUrlBuiltFromSystemProperties)).collect(Collectors.toList()));
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

  private boolean match(IWebStartable webStartable, String keyword) {
    return containsIgnoreCase(webStartable.getDisplayName(), keyword)
        || containsIgnoreCase(webStartable.getDescription(), keyword);
  }

  private IWorkflowSession getWorkflowSession(WebStartableSearchCriteria searchCriteria, IApplication application)
      throws Exception {
    if (searchCriteria.hasInvolvedUsername()) {
      IWorkflowSession givenUserWorkflowSession =
          findUserWorkflowSession(searchCriteria.getInvolvedUsername(), application);
      return givenUserWorkflowSession;
    }

    IWorkflowSession systemUserWorkflowSession =
        Ivy.wf().getWorkflowSession(application.getSecurityContext().getSystemUserSession());
    return systemUserWorkflowSession;
  }

  private static List<IApplication> getAllApplications() {
    return ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
  }
}
