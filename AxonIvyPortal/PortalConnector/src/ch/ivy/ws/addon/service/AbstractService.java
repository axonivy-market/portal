package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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

// import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Abstract service implementation that provides some basic operations
 * 
 * @author mde
 *
 */

public abstract class AbstractService {

  /**
   * Find all users on the server
   * 
   * @return list of users
   * @throws Exception
   */
  /**
   * @param apps
   * @param username
   * @return list of {@link IUser}
   * @throws Exception
   */
  protected List<IUser> findUsers(final java.util.List<String> apps, final String username) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<IUser>>() {
      @Override
      public List<IUser> call() throws Exception {
        List<IUser> result = new ArrayList<IUser>();

        IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();

        for (IApplication i : server.getApplicationConfigurationManager().getApplications()) {
          if (apps.contains(i.getName())) {
            if (i.getSecurityContext().findUser(username) != null) {
              result.add(i.getSecurityContext().findUser(username));
            }
          }
        }

        return result;
      }
    });
  }

  /**
   * Return List of WSExceptions if there are not some applications on the server or they are
   * inactive.
   * 
   * @param apps
   * @return List<WSException> specified exception
   * @throws Exception General exception
   * @see WSException
   */
  protected AvailableAppsResult findAvailableApplicationsAndUsers(final java.util.List<String> apps,
      final String username) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<AvailableAppsResult>() {
      @Override
      public AvailableAppsResult call() throws Exception {
        AvailableAppsResult result = new AvailableAppsResult();

        result.setUsers(new ArrayList<IUser>());

        java.util.List<WSException> errors = new ArrayList<WSException>();
        IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();

        if (apps.size() > 0) {
          boolean userNotFound = false;

          java.util.List<String> notFoundApps = new ArrayList<String>();
          java.util.List<String> notActiveApps = new ArrayList<String>();
          java.util.List<String> availableApps = new ArrayList<String>();

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

          if (notFoundApps.size() > 0) {
            List<Object> userText = new ArrayList<Object>();
            userText.add(parseList(notFoundApps));
            errors.add(new WSException(WSErrorType.WARNING, 10030, userText, null));
          }

          if (notActiveApps.size() > 0) {
            List<Object> userText = new ArrayList<Object>();
            userText.add(parseList(notActiveApps));
            errors.add(new WSException(WSErrorType.WARNING, 10023, userText, null));
          }

          if (userNotFound) {
            // No cases found, user is not valid
            List<Object> userText = new ArrayList<Object>();
            userText.add(username);
            errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
          }

        } else {
          // No application given
          errors.add(new WSException(WSErrorType.WARNING, 10026, null, null));
        }

        result.setErrors(errors);

        return result;
      }
    });
  }

  /**
   * 
   * @param username
   * @return user workflow session
   * @throws Exception
   */
  protected IWorkflowSession findUserWorkflowSession(final String username, final IApplication app) throws Exception {
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
   * @throws Exception
   */
  protected IvyApplication isApplicationActive(final String name) throws Exception {
    IvyApplication ivyApplication = new IvyApplication();
    IApplication application = getApplicationByName(name);
    if (application != null) {
      ivyApplication.setIsActive(application.getActivityOperationState().equals(ActivityOperationState.ACTIVE));
      ivyApplication.setName(name);
      ivyApplication.setId(application.getId());
    }
    return ivyApplication;
  }

  protected IApplication getApplicationByName(final String applicationName) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
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
