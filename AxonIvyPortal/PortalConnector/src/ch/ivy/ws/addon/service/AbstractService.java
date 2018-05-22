package ch.ivy.ws.addon.service;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
   * 
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
   * Return List of WSExceptions if there are not some applications on the server or they are inactive.
   * 
   * @param apps
   * @return List<WSException> specified exception
   */
  protected AvailableAppsResult findAvailableApplicationsAndUsers(final List<String> apps, final String username) {
    return executeAsSystem(() -> {
      if (apps.isEmpty()) {
        return resultOf(Arrays.asList(new WSException(WSErrorType.WARNING, 10026, null, null)));
      }
      return findAvailableAppsAndUsers(apps, username);
    });
  }

  private AvailableAppsResult findAvailableAppsAndUsers(final List<String> apps, final String username) {
    AvailableAppsResult result = initAvailableAppsResult();
    boolean userNotFound = false;
    List<String> notFoundApps = new ArrayList<>();
    List<String> notActiveApps = new ArrayList<>();
    List<IApplication> serverApps = getServer().getApplicationConfigurationManager().getApplications();
    for (String app : apps) {
      Optional<IApplication> serverApp = serverApps.stream().filter(sApp -> sApp.getName().equals(app)).findFirst();
      if (!serverApp.isPresent()) {
        notFoundApps.add(app);
        continue;
      }
      IUser user = serverApp.get().getSecurityContext().findUser(username);
      if (user != null) {
        if (serverApp.get().getActivityOperationState().equals(ActivityOperationState.ACTIVE)) {
          result.getAvailableApps().add(app);
          result.getUsers().add(user);
        } else {
          notActiveApps.add(app);
        }
      } else {
        userNotFound = true;
      }
    }
    result.getErrors().addAll(getErrors(username, userNotFound, notFoundApps, notActiveApps));
    return result;
  }

  private List<WSException> getErrors(final String username, boolean userNotFound, List<String> notFoundApps,
      List<String> notActiveApps) {
    List<WSException> errors = new ArrayList<>();
    if (!notFoundApps.isEmpty()) {
      errors.add(new WSException(WSErrorType.WARNING, 10030, Arrays.asList(parseList(notFoundApps)), null));
    }
    if (!notActiveApps.isEmpty()) {
      errors.add(new WSException(WSErrorType.WARNING, 10023, Arrays.asList(parseList(notActiveApps)), null));
    }
    if (userNotFound) {
      // No cases found, user is not valid
      errors.add(new WSException(WSErrorType.WARNING, 10029, Arrays.asList(username), null));
    }
    return errors;
  }

  private AvailableAppsResult initAvailableAppsResult() {
    AvailableAppsResult result = new AvailableAppsResult();
    result.setUsers(new ArrayList<IUser>());
    result.setAvailableApps(new ArrayList<>());
    result.setErrors(new ArrayList<>());
    return result;
  }

  private AvailableAppsResult resultOf(List<WSException> errors) {
    AvailableAppsResult result = new AvailableAppsResult();
    result.setErrors(errors);
    return result;
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
          return Ivy.wf().getWorkflowSession(session);
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
      return server.getApplicationConfigurationManager().findApplication(applicationName);
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
