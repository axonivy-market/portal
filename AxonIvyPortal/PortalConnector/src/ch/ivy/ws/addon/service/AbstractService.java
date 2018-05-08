package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.PortalException;
import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

/**
 * Abstract service implementation that provides some basic operations
 */

public abstract class AbstractService {

  /**
   * Find all users on the server
   * @param apps
   * @param username
   * @return list of {@link IUser}
   */
  protected List<IUser> findUsers(final java.util.List<String> apps, final String username) {
    return executeAsSystem(() -> {
      List<IUser> result = new ArrayList<>();

      IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();

      for (IApplication i : server.getApplicationConfigurationManager().getApplications()) {
        if (apps.contains(i.getName()) && i.getSecurityContext().findUser(username) != null) {
          result.add(i.getSecurityContext().findUser(username));
        }
      }

      return result;
    });
  }

  protected <T> T executeAsSystem(Callable<T> callable) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(callable);
    } catch (Exception ex) {
      throw new PortalException(ex);

    }
  }

  /**
   * Return List of WSExceptions if there are not some applications on the server or they are
   * inactive.
   * 
   * @param apps
   * @return List<WSException> specified exception
   */
  protected AvailableAppsResult findAvailableApplicationsAndUsers(final java.util.List<String> apps,
      final String username) {
    return executeAsSystem(() -> {
      AvailableAppsResult result = new AvailableAppsResult();

      result.setUsers(new ArrayList<IUser>());

      java.util.List<WSException> errors = new ArrayList<>();
      IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();

      if (!apps.isEmpty()) {
        boolean userNotFound = false;

        java.util.List<String> notFoundApps = new ArrayList<>();
        java.util.List<String> notActiveApps = new ArrayList<>();
        java.util.List<String> availableApps = new ArrayList<>();

        java.util.List<IApplication> serverApps = server.getApplicationConfigurationManager().getApplications();

        for (String app : apps) {
          Boolean available = false;
          Boolean appFound = false;
          for (IApplication serverApp : serverApps) {

            if (serverApp.getName().equals(app)) {
              IUser u = serverApp.getSecurityContext().findUser(username);

              if (u != null) {
                if (serverApp.getActivityOperationState().equals(ActivityOperationState.ACTIVE)) {
                  available = true;
                  result.getUsers().add(u);
                } else {
                  notActiveApps.add(app);
                }
              } else {
                userNotFound = true;
              }

              appFound = true;
              break;
            }
          }
          if (!appFound) {
            notFoundApps.add(app);
            available = false;
          }
          if (available) {
            availableApps.add(app);
          }
        }

        result.setAvailableApps(availableApps);

        if (!notFoundApps.isEmpty()) {
          List<Object> userText = new ArrayList<>();
          userText.add(parseList(notFoundApps));
          errors.add(new WSException(WSErrorType.WARNING, 10030, userText, null));
        }

        if (!notActiveApps.isEmpty()) {
          List<Object> userText = new ArrayList<>();
          userText.add(parseList(notActiveApps));
          errors.add(new WSException(WSErrorType.WARNING, 10023, userText, null));
        }

        if (userNotFound) {
          // No cases found, user is not valid
          List<Object> userText = new ArrayList<>();
          userText.add(username);
          errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
        }

      } else {
        // No application given
        errors.add(new WSException(WSErrorType.WARNING, 10026, null, null));
      }

      result.setErrors(errors);

      return result;
    });
  }

  /**
   * 
   * @param username
   * @return user workflow session
   * @throws WSException
   */
  protected IWorkflowSession findUserWorkflowSession(final String username, final IApplication app) throws WSException {
    try {
      final ISecurityContext securityContext = app.getSecurityContext();
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IWorkflowSession>() {
        @Override
        public IWorkflowSession call() throws Exception {
          ISession session = securityContext.createSession();
          IUser user = securityContext.findUser(username);

          if (user != null) {
            String authenticationMode = "customAuth";
            session.authenticateSessionUser(user, authenticationMode, Ivy.wfTask().getId());
          }
          IWorkflowSession workflowSession = Ivy.wf().getWorkflowSession(session);
          return workflowSession;
        }
      });
    } catch (Exception e) {
      throw new WSException(e);

    }
  }

  /**
   * 
   * @param name
   * @return application
   */
  protected IvyApplication isApplicationActive(final String name) {
    IvyApplication ivyApplication = new IvyApplication();
    IApplication application = getApplicationByName(name);
    if (application != null) {
      ivyApplication.setIsActive(application.getActivityOperationState().equals(ActivityOperationState.ACTIVE));
      ivyApplication.setName(name);
      ivyApplication.setId(application.getId());
    }
    return ivyApplication;
  }

  protected IApplication getApplicationByName(final String applicationName) {
    return executeAsSystem(() -> {
      IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
      IApplication application = server.getApplicationConfigurationManager().findApplication(applicationName);
      return application;
    });
  }

  /**
   * Returns String representation of given List<String> items. String are separated by ", ".
   * 
   * @param dataList
   * @return String resultData
   */
  protected String parseList(java.util.List<String> dataList) {
    final String separator = ", ";
    String resultData = "";

    for (String data : dataList) {
      resultData += separator + data;
    }

    resultData = resultData.substring(separator.length());

    return resultData;
  }
}
